package se.fluff.aptusviewer.db;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.ResultSetHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import se.fluff.aptusviewer.models.db.*;
import se.fluff.aptusviewer.models.gui.AptusRow;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class DatabaseRepository {

    private Connection connection;
    private final String connectionstring;

    public DatabaseRepository(String hostname, String dbname, String username, String password) {

        // jdbc:sqlserver://172.16.20.254;databaseName=MultiAccess;encrypt=true;trustServerCertificate=true;user=fluff;password=password
        this.connectionstring = "jdbc:sqlserver://" + hostname +
                ";databaseName=" +
                dbname +
                ";encrypt=true;trustServerCertificate=true;user=" +
                username +
                ";password=" +
                password;
    }

    private boolean open() {
        try {
            if(this.connection == null) {
                this.connection = DriverManager.getConnection(this.connectionstring);
            }
            else if(this.connection.isClosed()) {
                this.connection = DriverManager.getConnection(this.connectionstring);
            }
            return true;
        }
        catch(Exception e) {
            System.err.println(e.getMessage());
            return false;
        }
    }


    public List<AptusObject> getObjects() {
        List<AptusObject> aos = new ArrayList<>();

        ResultSetHandler<List<AptusObject>> h = new BeanListHandler<>(AptusObject.class);

        if(this.open()) {
            QueryRunner run = new QueryRunner();

            try {
                aos = run.query(this.connection, "SELECT * FROM dbo.Object", h);
                return aos;
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }

        return aos;

    }

    public List<Customer> getCustomers() {
        List<Customer> customers = new ArrayList<>();
        ResultSetHandler<List<Customer>> h = new BeanListHandler<>(Customer.class);

        if(this.open()) {
            QueryRunner run = new QueryRunner();

            try {
                customers = run.query(this.connection, "SELECT * FROM dbo.Customer", h);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return customers;
    }

    public List<AptusRow> getVirtualAptusRows() {
        List<AptusRow> aptusRows = new ArrayList<>();
        ResultSetHandler<List<AptusRow>> h = new BeanListHandler<>(AptusRow.class);

        if(this.open()) {
            QueryRunner run = new QueryRunner();

            try {
                aptusRows = run.query(this.connection, "" +
                        "SELECT o.Id as objectId, o.Name as objectName, o.Address_Id as addressId, ApartmentPhoneAddress as phoneAddress, Floor as floor, c.Id as customerId, c.Name as customer, FirstName, Surname " +
                        "FROM Object o " +
                        "LEFT JOIN ObjectInCustomer oic ON oic.Object_ID = o.Id " +
                        "LEFT JOIN Customer c ON c.Id = oic.Customer_Id " +
                        "LEFT JOIN CustomerPerson cp on cp.ObjectInCustomer_Id = oic.Id ", h);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return aptusRows;

    }

    public List<Authority> getAuthoritiesForObjectId(long objectId) {
        List<Authority> authorities = new ArrayList<>();
        ResultSetHandler<List<Authority>> h = new BeanListHandler<>(Authority.class);

        if(this.open()) {
            QueryRunner run = new QueryRunner();

            try {
                authorities = run.query(this.connection, "SELECT * FROM Authority a LEFT JOIN AuthorityInObject aio ON aio.Authority_Id = a.Id WHERE aio.Object_Id = ?", h, objectId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return authorities;
    }

    public List<Authority> getAuthoritiesForUserId(long userId) {
        List<Authority> authorities = new ArrayList<>();
        ResultSetHandler<List<Authority>> h = new BeanListHandler<>(Authority.class);

        if(this.open()) {
            QueryRunner run = new QueryRunner();

            try {
                authorities = run.query(this.connection, "SELECT * FROM Authority a LEFT JOIN AuthorityInUser aiu ON aiu.AuthorityId = a.Id WHERE aiu.userId = ? AND aiu.removedDate IS NULL", h, userId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return authorities;
    }

    public List<User> getUsersForCustomerId(long customerId) {
        List<User> authorities = new ArrayList<>();
        ResultSetHandler<List<User>> h = new BeanListHandler<>(User.class);

        if(this.open()) {
            QueryRunner run = new QueryRunner();

            try {
                authorities = run.query(this.connection, "SELECT * FROM Users WHERE customerId = ?", h, customerId);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }

        }
        return authorities;
    }

    public List<AptusEvent> getEventsForCustomerId(long customerId) {
        List<AptusEvent> events = new ArrayList<>();
        ResultSetHandler<List<AptusEvent>> h = new EventListHandler();

        if(this.open()) {
            QueryRunner run = new QueryRunner();
            try {
                events = run.query(this.connection, "SELECT * FROM Event WHERE Customer_Id = ?", h, customerId);
            }
            catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return events;
    }

    public Map<Integer, AptusControl> getControls() {
        Map<Integer, AptusControl> controls = new HashMap<>();
        ResultSetHandler<List<AptusControl>> h = new ControlListHandler();

        if(this.open()) {
            QueryRunner run = new QueryRunner();
            try {
                controls = run.query(this.connection, "SELECT * FROM Control", h)
                        .stream()
                        .collect(Collectors.toMap(AptusControl::getId, c -> c));
            }
            catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return controls;
    }

    public Map<Integer, AptusSystem> getSystems() {
        Map<Integer, AptusSystem> systems = new HashMap<>();
        ResultSetHandler<List<AptusSystem>> h = new AptusSystemListHandler();

        if(this.open()) {
            QueryRunner run = new QueryRunner();
            try {
                systems = run.query(this.connection, "SELECT * FROM System", h)
                        .stream()
                        .collect(Collectors.toMap(AptusSystem::getId, c -> c));
            }
            catch(SQLException e) {
                throw new RuntimeException(e);
            }
        }

        return systems;
    }

}
