package com.orasystems.libs.utils.log.model;

import java.util.Date;

import com.j256.ormlite.field.DataType;
import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName="LOG")
public class LogDB {

	public static final String PK_ID = "ID";
    @DatabaseField(columnName = PK_ID, canBeNull = false, unique = true, generatedId = true)
    private long id;
    
    public static final String COLUMN_LOG = "LOG_DESC";
    @DatabaseField(columnName = COLUMN_LOG,useGetSet = true,canBeNull=false,defaultValue="",dataType=DataType.LONG_STRING)
    private String log;
    
    public static final String TIPO_TODOS = "Todos";
    public static final String TIPO_INFO = "Informação";
    public static final String TIPO_WARN = "Aviso";
    public static final String TIPO_ERROR = "Erro";
    public static final String COLUMN_TIPO = "LOG_TIPO";
    @DatabaseField(columnName = COLUMN_TIPO,useGetSet = true,canBeNull=false,defaultValue="")
    private String tipo;
    
    public static final String COLUMN_MESSAGE = "LOG_MESSAGE";
    @DatabaseField(columnName = COLUMN_MESSAGE,useGetSet = true,canBeNull=false,defaultValue="")
    private String message;
    
    public static final String COLUMN_METODO = "LOG_METODO";
    @DatabaseField(columnName = COLUMN_METODO,useGetSet = true,canBeNull=false,defaultValue="")
    private String metodo;
    
    public static final String COLUMN_DATA = "LOG_DATA";
    @DatabaseField(columnName = COLUMN_DATA,useGetSet = true)
    private Date data;
    
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getLog() {
		return log;
	}
	public void setLog(String log) {
		this.log = log;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Date getData() {
		return data;
	}
	public void setData(Date data) {
		this.data = data;
	}
	public String getMetodo() {
		return metodo;
	}
	public void setMetodo(String metodo) {
		this.metodo = metodo;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
}
