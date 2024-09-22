package se.fluff.aptusviewer.db;

import org.apache.commons.dbutils.BasicRowProcessor;
import org.apache.commons.dbutils.BeanProcessor;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import se.fluff.aptusviewer.models.db.AptusSystem;

import java.util.HashMap;
import java.util.Map;

public class AptusSystemListHandler extends BeanListHandler<AptusSystem> {

    public AptusSystemListHandler() {
        super(AptusSystem.class, new BasicRowProcessor(new BeanProcessor(getColumnsToFieldsMap())));
    }

    public static Map<String, String> getColumnsToFieldsMap() {
        Map<String, String> columnsToFieldsMap = new HashMap<>();
        columnsToFieldsMap.put("Name", "name");
        columnsToFieldsMap.put("ProgCode", "progCode");
        columnsToFieldsMap.put("APBtime", "apbtime");
        columnsToFieldsMap.put("Flags", "flags");
        columnsToFieldsMap.put("ComPort", "comPort");
        columnsToFieldsMap.put("Baudrate", "baudrate");
        columnsToFieldsMap.put("ServerPort", "serverPort");
        columnsToFieldsMap.put("TelNo", "telNo");
        columnsToFieldsMap.put("ModemConf", "modemConf");
        columnsToFieldsMap.put("Computer", "computer");
        columnsToFieldsMap.put("ServerName", "serverName");
        columnsToFieldsMap.put("LastUpdateOk", "lastUpdateOk");
        columnsToFieldsMap.put("LastUpdateFail", "lastUpdateFail");
        columnsToFieldsMap.put("Id", "id");
        columnsToFieldsMap.put("CryptKey", "cryptKey");
        columnsToFieldsMap.put("NextCryptKey", "nextCryptKey");
        columnsToFieldsMap.put("FillLimit", "fillLimit");
        columnsToFieldsMap.put("ModemConfMaster", "modemConfMaster");
        columnsToFieldsMap.put("IsChanged", "isChanged");
        columnsToFieldsMap.put("LastContactMasterTime", "lastContactMasterTime");
        columnsToFieldsMap.put("LastContactFailedTime", "lastContactFailedTime");
        columnsToFieldsMap.put("Domain_Id", "domainId");
        return columnsToFieldsMap;
    }


}
