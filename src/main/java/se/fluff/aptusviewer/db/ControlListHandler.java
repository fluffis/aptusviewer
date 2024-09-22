package se.fluff.aptusviewer.db;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import se.fluff.aptusviewer.models.db.AptusControl;

import java.util.HashMap;
import java.util.Map;

public class ControlListHandler extends BeanListHandler<AptusControl> {

    public ControlListHandler() {
        super(AptusControl.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
    }

    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("SystemId", "systemId");
        columnsToFieldsMap.put("Name", "name");
        columnsToFieldsMap.put("Id", "id");
        columnsToFieldsMap.put("Flags", "flags");
        columnsToFieldsMap.put("Type", "type");
        columnsToFieldsMap.put("EventStart", "eventStart");
        columnsToFieldsMap.put("EventStop", "eventStop");
        columnsToFieldsMap.put("LastActiveTime", "lastActiveTime");
        columnsToFieldsMap.put("LastInactiveTime", "lastInactiveTime");

        return columnsToFieldsMap;
    }

}
