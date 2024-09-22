package se.fluff.aptusviewer.db;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import se.fluff.aptusviewer.models.db.AptusEvent;

import java.util.HashMap;
import java.util.Map;

public class EventListHandler extends BeanListHandler<AptusEvent> {

    public EventListHandler() {
        super(AptusEvent.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
    }

    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("eventNo", "eventNo");
        columnsToFieldsMap.put("Name", "name");
        columnsToFieldsMap.put("EventTime", "eventTime");
        columnsToFieldsMap.put("UserID", "userId");
        columnsToFieldsMap.put("ActivatorName", "activatorName");
        columnsToFieldsMap.put("Modified", "modified");
        columnsToFieldsMap.put("SystemID", "systemId");
        columnsToFieldsMap.put("ControlId", "controlId");
        columnsToFieldsMap.put("ResourceID", "resourceId");
        columnsToFieldsMap.put("BlockInputName", "blockInputName");
        columnsToFieldsMap.put("TimeStamp", "timestamp");
        columnsToFieldsMap.put("Global", "global");
        columnsToFieldsMap.put("BoardID", "boardId");
        columnsToFieldsMap.put("Id", "id");
        columnsToFieldsMap.put("CreatedTime", "createdTime");
        columnsToFieldsMap.put("Customer_Id", "customerId");
        return columnsToFieldsMap;
    }
}