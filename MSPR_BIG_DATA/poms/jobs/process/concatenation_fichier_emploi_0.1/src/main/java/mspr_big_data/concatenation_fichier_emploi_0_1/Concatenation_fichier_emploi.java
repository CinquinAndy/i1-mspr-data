// ============================================================================
//
// Copyright (c) 2006-2015, Talend SA
//
// Ce code source a été automatiquement généré par_Talend Open Studio for Data Integration
// / Soumis à la Licence Apache, Version 2.0 (la "Licence") ;
// votre utilisation de ce fichier doit respecter les termes de la Licence.
// Vous pouvez obtenir une copie de la Licence sur
// http://www.apache.org/licenses/LICENSE-2.0
// 
// Sauf lorsqu'explicitement prévu par la loi en vigueur ou accepté par écrit, le logiciel
// distribué sous la Licence est distribué "TEL QUEL",
// SANS GARANTIE OU CONDITION D'AUCUNE SORTE, expresse ou implicite.
// Consultez la Licence pour connaître la terminologie spécifique régissant les autorisations et
// les limites prévues par la Licence.

package mspr_big_data.concatenation_fichier_emploi_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: Concatenation_fichier_emploi Purpose: <br>
 * Description: <br>
 * 
 * @author user@talend.com
 * @version 8.0.1.20211109_1610
 * @status
 */
public class Concatenation_fichier_emploi implements TalendJob {

	protected static void logIgnoredError(String message, Throwable cause) {
		System.err.println(message);
		if (cause != null) {
			cause.printStackTrace();
		}

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "Concatenation_fichier_emploi";
	private final String projectName = "MSPR_BIG_DATA";
	public Integer errorCode = null;
	private String currentComponent = "";

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private RunStat runStat = new RunStat();

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;
		private String currentComponent = null;
		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					Concatenation_fichier_emploi.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(Concatenation_fichier_emploi.this,
									new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tFileInputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tMap_1_error(Exception exception, String errorComponent, final java.util.Map<String, Object> globalMap)
			throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileOutputExcel_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tFileInputExcel_1_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tFileInputExcel_1_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class Donnees_mergeesStruct implements routines.system.IPersistableRow<Donnees_mergeesStruct> {
		final static byte[] commonByteArrayLock_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[0];
		static byte[] commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[0];
		protected static final int DEFAULT_HASHCODE = 1;
		protected static final int PRIME = 31;
		protected int hashCode = DEFAULT_HASHCODE;
		public boolean hashCodeDirty = true;

		public String loopKey;

		public String Nom_serie;

		public String getNom_serie() {
			return this.Nom_serie;
		}

		public String Zone_geographique;

		public String getZone_geographique() {
			return this.Zone_geographique;
		}

		public Double T42001;

		public Double getT42001() {
			return this.T42001;
		}

		public Double T12002;

		public Double getT12002() {
			return this.T12002;
		}

		public Double T22002;

		public Double getT22002() {
			return this.T22002;
		}

		public Double T32002;

		public Double getT32002() {
			return this.T32002;
		}

		public Double T42002;

		public Double getT42002() {
			return this.T42002;
		}

		public Double T12003;

		public Double getT12003() {
			return this.T12003;
		}

		public Double T22003;

		public Double getT22003() {
			return this.T22003;
		}

		public Double T32003;

		public Double getT32003() {
			return this.T32003;
		}

		public Double T42003;

		public Double getT42003() {
			return this.T42003;
		}

		public Double T12004;

		public Double getT12004() {
			return this.T12004;
		}

		public Double T22004;

		public Double getT22004() {
			return this.T22004;
		}

		public Double T32004;

		public Double getT32004() {
			return this.T32004;
		}

		public Double T42004;

		public Double getT42004() {
			return this.T42004;
		}

		public Double T12005;

		public Double getT12005() {
			return this.T12005;
		}

		public Double T22005;

		public Double getT22005() {
			return this.T22005;
		}

		public Double T32005;

		public Double getT32005() {
			return this.T32005;
		}

		public Double T42005;

		public Double getT42005() {
			return this.T42005;
		}

		public Double T12006;

		public Double getT12006() {
			return this.T12006;
		}

		public Double T22006;

		public Double getT22006() {
			return this.T22006;
		}

		public Double T32006;

		public Double getT32006() {
			return this.T32006;
		}

		public Double T42006;

		public Double getT42006() {
			return this.T42006;
		}

		public Double T12007;

		public Double getT12007() {
			return this.T12007;
		}

		public Double T22007;

		public Double getT22007() {
			return this.T22007;
		}

		public Double T32007;

		public Double getT32007() {
			return this.T32007;
		}

		public Double T42007;

		public Double getT42007() {
			return this.T42007;
		}

		public Double T12008;

		public Double getT12008() {
			return this.T12008;
		}

		public Double T22008;

		public Double getT22008() {
			return this.T22008;
		}

		public Double T32008;

		public Double getT32008() {
			return this.T32008;
		}

		public Double T42008;

		public Double getT42008() {
			return this.T42008;
		}

		public Double T12009;

		public Double getT12009() {
			return this.T12009;
		}

		public Double T22009;

		public Double getT22009() {
			return this.T22009;
		}

		public Double T32009;

		public Double getT32009() {
			return this.T32009;
		}

		public Double T42009;

		public Double getT42009() {
			return this.T42009;
		}

		public Double T12010;

		public Double getT12010() {
			return this.T12010;
		}

		public Double T22010;

		public Double getT22010() {
			return this.T22010;
		}

		public Double T32010;

		public Double getT32010() {
			return this.T32010;
		}

		public Double T42010;

		public Double getT42010() {
			return this.T42010;
		}

		public Double T12011;

		public Double getT12011() {
			return this.T12011;
		}

		public Double T22011;

		public Double getT22011() {
			return this.T22011;
		}

		public Double T32011;

		public Double getT32011() {
			return this.T32011;
		}

		public Double T42011;

		public Double getT42011() {
			return this.T42011;
		}

		public Double T12012;

		public Double getT12012() {
			return this.T12012;
		}

		public Double T22012;

		public Double getT22012() {
			return this.T22012;
		}

		public Double T32012;

		public Double getT32012() {
			return this.T32012;
		}

		public Double T42012;

		public Double getT42012() {
			return this.T42012;
		}

		public Double T12013;

		public Double getT12013() {
			return this.T12013;
		}

		public Double T22013;

		public Double getT22013() {
			return this.T22013;
		}

		public Double T32013;

		public Double getT32013() {
			return this.T32013;
		}

		public Double T42013;

		public Double getT42013() {
			return this.T42013;
		}

		public Double T12014;

		public Double getT12014() {
			return this.T12014;
		}

		public Double T22014;

		public Double getT22014() {
			return this.T22014;
		}

		public Double T32014;

		public Double getT32014() {
			return this.T32014;
		}

		public Double T42014;

		public Double getT42014() {
			return this.T42014;
		}

		public Double T12015;

		public Double getT12015() {
			return this.T12015;
		}

		public Double T22015;

		public Double getT22015() {
			return this.T22015;
		}

		public Double T32015;

		public Double getT32015() {
			return this.T32015;
		}

		public Double T42015;

		public Double getT42015() {
			return this.T42015;
		}

		public Double T12016;

		public Double getT12016() {
			return this.T12016;
		}

		public Double T22016;

		public Double getT22016() {
			return this.T22016;
		}

		public Double T32016;

		public Double getT32016() {
			return this.T32016;
		}

		public Double T42016;

		public Double getT42016() {
			return this.T42016;
		}

		public Double T12017;

		public Double getT12017() {
			return this.T12017;
		}

		public Double T22017;

		public Double getT22017() {
			return this.T22017;
		}

		public Double T32017;

		public Double getT32017() {
			return this.T32017;
		}

		public Double T42017;

		public Double getT42017() {
			return this.T42017;
		}

		public Double T12018;

		public Double getT12018() {
			return this.T12018;
		}

		public Double T22018;

		public Double getT22018() {
			return this.T22018;
		}

		public Double T32018;

		public Double getT32018() {
			return this.T32018;
		}

		public Double T42018;

		public Double getT42018() {
			return this.T42018;
		}

		public Double T12019;

		public Double getT12019() {
			return this.T12019;
		}

		public Double T22019;

		public Double getT22019() {
			return this.T22019;
		}

		public Double T32019;

		public Double getT32019() {
			return this.T32019;
		}

		public Double T42019;

		public Double getT42019() {
			return this.T42019;
		}

		public Double T12020;

		public Double getT12020() {
			return this.T12020;
		}

		public Double T22020;

		public Double getT22020() {
			return this.T22020;
		}

		public Double T32020;

		public Double getT32020() {
			return this.T32020;
		}

		public Double T42020;

		public Double getT42020() {
			return this.T42020;
		}

		public Double T12021;

		public Double getT12021() {
			return this.T12021;
		}

		public Double T22021;

		public Double getT22021() {
			return this.T22021;
		}

		public Double T32021;

		public Double getT32021() {
			return this.T32021;
		}

		public Double T42021;

		public Double getT42021() {
			return this.T42021;
		}

		public Double T12022;

		public Double getT12022() {
			return this.T12022;
		}

		public Double T22022;

		public Double getT22022() {
			return this.T22022;
		}

		public Double T32022;

		public Double getT32022() {
			return this.T32022;
		}

		public Double T42022;

		public Double getT42022() {
			return this.T42022;
		}

		@Override
		public int hashCode() {
			if (this.hashCodeDirty) {
				final int prime = PRIME;
				int result = DEFAULT_HASHCODE;

				result = prime * result + ((this.Nom_serie == null) ? 0 : this.Nom_serie.hashCode());

				this.hashCode = result;
				this.hashCodeDirty = false;
			}
			return this.hashCode;
		}

		@Override
		public boolean equals(Object obj) {
			if (this == obj)
				return true;
			if (obj == null)
				return false;
			if (getClass() != obj.getClass())
				return false;
			final Donnees_mergeesStruct other = (Donnees_mergeesStruct) obj;

			if (this.Nom_serie == null) {
				if (other.Nom_serie != null)
					return false;

			} else if (!this.Nom_serie.equals(other.Nom_serie))

				return false;

			return true;
		}

		public void copyDataTo(Donnees_mergeesStruct other) {

			other.Nom_serie = this.Nom_serie;
			other.Zone_geographique = this.Zone_geographique;
			other.T42001 = this.T42001;
			other.T12002 = this.T12002;
			other.T22002 = this.T22002;
			other.T32002 = this.T32002;
			other.T42002 = this.T42002;
			other.T12003 = this.T12003;
			other.T22003 = this.T22003;
			other.T32003 = this.T32003;
			other.T42003 = this.T42003;
			other.T12004 = this.T12004;
			other.T22004 = this.T22004;
			other.T32004 = this.T32004;
			other.T42004 = this.T42004;
			other.T12005 = this.T12005;
			other.T22005 = this.T22005;
			other.T32005 = this.T32005;
			other.T42005 = this.T42005;
			other.T12006 = this.T12006;
			other.T22006 = this.T22006;
			other.T32006 = this.T32006;
			other.T42006 = this.T42006;
			other.T12007 = this.T12007;
			other.T22007 = this.T22007;
			other.T32007 = this.T32007;
			other.T42007 = this.T42007;
			other.T12008 = this.T12008;
			other.T22008 = this.T22008;
			other.T32008 = this.T32008;
			other.T42008 = this.T42008;
			other.T12009 = this.T12009;
			other.T22009 = this.T22009;
			other.T32009 = this.T32009;
			other.T42009 = this.T42009;
			other.T12010 = this.T12010;
			other.T22010 = this.T22010;
			other.T32010 = this.T32010;
			other.T42010 = this.T42010;
			other.T12011 = this.T12011;
			other.T22011 = this.T22011;
			other.T32011 = this.T32011;
			other.T42011 = this.T42011;
			other.T12012 = this.T12012;
			other.T22012 = this.T22012;
			other.T32012 = this.T32012;
			other.T42012 = this.T42012;
			other.T12013 = this.T12013;
			other.T22013 = this.T22013;
			other.T32013 = this.T32013;
			other.T42013 = this.T42013;
			other.T12014 = this.T12014;
			other.T22014 = this.T22014;
			other.T32014 = this.T32014;
			other.T42014 = this.T42014;
			other.T12015 = this.T12015;
			other.T22015 = this.T22015;
			other.T32015 = this.T32015;
			other.T42015 = this.T42015;
			other.T12016 = this.T12016;
			other.T22016 = this.T22016;
			other.T32016 = this.T32016;
			other.T42016 = this.T42016;
			other.T12017 = this.T12017;
			other.T22017 = this.T22017;
			other.T32017 = this.T32017;
			other.T42017 = this.T42017;
			other.T12018 = this.T12018;
			other.T22018 = this.T22018;
			other.T32018 = this.T32018;
			other.T42018 = this.T42018;
			other.T12019 = this.T12019;
			other.T22019 = this.T22019;
			other.T32019 = this.T32019;
			other.T42019 = this.T42019;
			other.T12020 = this.T12020;
			other.T22020 = this.T22020;
			other.T32020 = this.T32020;
			other.T42020 = this.T42020;
			other.T12021 = this.T12021;
			other.T22021 = this.T22021;
			other.T32021 = this.T32021;
			other.T42021 = this.T42021;
			other.T12022 = this.T12022;
			other.T22022 = this.T22022;
			other.T32022 = this.T32022;
			other.T42022 = this.T42022;

		}

		public void copyKeysDataTo(Donnees_mergeesStruct other) {

			other.Nom_serie = this.Nom_serie;

		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi.length) {
					if (length < 1024 && commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi.length == 0) {
						commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[1024];
					} else {
						commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi, 0, length);
				strReturn = new String(commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi.length) {
					if (length < 1024 && commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi.length == 0) {
						commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[1024];
					} else {
						commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi, 0, length);
				strReturn = new String(commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MSPR_BIG_DATA_Concatenation_fichier_emploi) {

				try {

					int length = 0;

					this.Nom_serie = readString(dis);

					this.Zone_geographique = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.T42001 = null;
					} else {
						this.T42001 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12002 = null;
					} else {
						this.T12002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22002 = null;
					} else {
						this.T22002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32002 = null;
					} else {
						this.T32002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42002 = null;
					} else {
						this.T42002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12003 = null;
					} else {
						this.T12003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22003 = null;
					} else {
						this.T22003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32003 = null;
					} else {
						this.T32003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42003 = null;
					} else {
						this.T42003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12004 = null;
					} else {
						this.T12004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22004 = null;
					} else {
						this.T22004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32004 = null;
					} else {
						this.T32004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42004 = null;
					} else {
						this.T42004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12005 = null;
					} else {
						this.T12005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22005 = null;
					} else {
						this.T22005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32005 = null;
					} else {
						this.T32005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42005 = null;
					} else {
						this.T42005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12006 = null;
					} else {
						this.T12006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22006 = null;
					} else {
						this.T22006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32006 = null;
					} else {
						this.T32006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42006 = null;
					} else {
						this.T42006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12007 = null;
					} else {
						this.T12007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22007 = null;
					} else {
						this.T22007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32007 = null;
					} else {
						this.T32007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42007 = null;
					} else {
						this.T42007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12008 = null;
					} else {
						this.T12008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22008 = null;
					} else {
						this.T22008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32008 = null;
					} else {
						this.T32008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42008 = null;
					} else {
						this.T42008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12009 = null;
					} else {
						this.T12009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22009 = null;
					} else {
						this.T22009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32009 = null;
					} else {
						this.T32009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42009 = null;
					} else {
						this.T42009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12010 = null;
					} else {
						this.T12010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22010 = null;
					} else {
						this.T22010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32010 = null;
					} else {
						this.T32010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42010 = null;
					} else {
						this.T42010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12011 = null;
					} else {
						this.T12011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22011 = null;
					} else {
						this.T22011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32011 = null;
					} else {
						this.T32011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42011 = null;
					} else {
						this.T42011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12012 = null;
					} else {
						this.T12012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22012 = null;
					} else {
						this.T22012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32012 = null;
					} else {
						this.T32012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42012 = null;
					} else {
						this.T42012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12013 = null;
					} else {
						this.T12013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22013 = null;
					} else {
						this.T22013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32013 = null;
					} else {
						this.T32013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42013 = null;
					} else {
						this.T42013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12014 = null;
					} else {
						this.T12014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22014 = null;
					} else {
						this.T22014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32014 = null;
					} else {
						this.T32014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42014 = null;
					} else {
						this.T42014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12015 = null;
					} else {
						this.T12015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22015 = null;
					} else {
						this.T22015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32015 = null;
					} else {
						this.T32015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42015 = null;
					} else {
						this.T42015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12016 = null;
					} else {
						this.T12016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22016 = null;
					} else {
						this.T22016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32016 = null;
					} else {
						this.T32016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42016 = null;
					} else {
						this.T42016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12017 = null;
					} else {
						this.T12017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22017 = null;
					} else {
						this.T22017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32017 = null;
					} else {
						this.T32017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42017 = null;
					} else {
						this.T42017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12018 = null;
					} else {
						this.T12018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22018 = null;
					} else {
						this.T22018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32018 = null;
					} else {
						this.T32018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42018 = null;
					} else {
						this.T42018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12019 = null;
					} else {
						this.T12019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22019 = null;
					} else {
						this.T22019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32019 = null;
					} else {
						this.T32019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42019 = null;
					} else {
						this.T42019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12020 = null;
					} else {
						this.T12020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22020 = null;
					} else {
						this.T22020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32020 = null;
					} else {
						this.T32020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42020 = null;
					} else {
						this.T42020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12021 = null;
					} else {
						this.T12021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22021 = null;
					} else {
						this.T22021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32021 = null;
					} else {
						this.T32021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42021 = null;
					} else {
						this.T42021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12022 = null;
					} else {
						this.T12022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22022 = null;
					} else {
						this.T22022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32022 = null;
					} else {
						this.T32022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42022 = null;
					} else {
						this.T42022 = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MSPR_BIG_DATA_Concatenation_fichier_emploi) {

				try {

					int length = 0;

					this.Nom_serie = readString(dis);

					this.Zone_geographique = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.T42001 = null;
					} else {
						this.T42001 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12002 = null;
					} else {
						this.T12002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22002 = null;
					} else {
						this.T22002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32002 = null;
					} else {
						this.T32002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42002 = null;
					} else {
						this.T42002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12003 = null;
					} else {
						this.T12003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22003 = null;
					} else {
						this.T22003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32003 = null;
					} else {
						this.T32003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42003 = null;
					} else {
						this.T42003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12004 = null;
					} else {
						this.T12004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22004 = null;
					} else {
						this.T22004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32004 = null;
					} else {
						this.T32004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42004 = null;
					} else {
						this.T42004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12005 = null;
					} else {
						this.T12005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22005 = null;
					} else {
						this.T22005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32005 = null;
					} else {
						this.T32005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42005 = null;
					} else {
						this.T42005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12006 = null;
					} else {
						this.T12006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22006 = null;
					} else {
						this.T22006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32006 = null;
					} else {
						this.T32006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42006 = null;
					} else {
						this.T42006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12007 = null;
					} else {
						this.T12007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22007 = null;
					} else {
						this.T22007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32007 = null;
					} else {
						this.T32007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42007 = null;
					} else {
						this.T42007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12008 = null;
					} else {
						this.T12008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22008 = null;
					} else {
						this.T22008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32008 = null;
					} else {
						this.T32008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42008 = null;
					} else {
						this.T42008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12009 = null;
					} else {
						this.T12009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22009 = null;
					} else {
						this.T22009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32009 = null;
					} else {
						this.T32009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42009 = null;
					} else {
						this.T42009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12010 = null;
					} else {
						this.T12010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22010 = null;
					} else {
						this.T22010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32010 = null;
					} else {
						this.T32010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42010 = null;
					} else {
						this.T42010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12011 = null;
					} else {
						this.T12011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22011 = null;
					} else {
						this.T22011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32011 = null;
					} else {
						this.T32011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42011 = null;
					} else {
						this.T42011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12012 = null;
					} else {
						this.T12012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22012 = null;
					} else {
						this.T22012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32012 = null;
					} else {
						this.T32012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42012 = null;
					} else {
						this.T42012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12013 = null;
					} else {
						this.T12013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22013 = null;
					} else {
						this.T22013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32013 = null;
					} else {
						this.T32013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42013 = null;
					} else {
						this.T42013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12014 = null;
					} else {
						this.T12014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22014 = null;
					} else {
						this.T22014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32014 = null;
					} else {
						this.T32014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42014 = null;
					} else {
						this.T42014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12015 = null;
					} else {
						this.T12015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22015 = null;
					} else {
						this.T22015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32015 = null;
					} else {
						this.T32015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42015 = null;
					} else {
						this.T42015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12016 = null;
					} else {
						this.T12016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22016 = null;
					} else {
						this.T22016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32016 = null;
					} else {
						this.T32016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42016 = null;
					} else {
						this.T42016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12017 = null;
					} else {
						this.T12017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22017 = null;
					} else {
						this.T22017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32017 = null;
					} else {
						this.T32017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42017 = null;
					} else {
						this.T42017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12018 = null;
					} else {
						this.T12018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22018 = null;
					} else {
						this.T22018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32018 = null;
					} else {
						this.T32018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42018 = null;
					} else {
						this.T42018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12019 = null;
					} else {
						this.T12019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22019 = null;
					} else {
						this.T22019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32019 = null;
					} else {
						this.T32019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42019 = null;
					} else {
						this.T42019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12020 = null;
					} else {
						this.T12020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22020 = null;
					} else {
						this.T22020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32020 = null;
					} else {
						this.T32020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42020 = null;
					} else {
						this.T42020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12021 = null;
					} else {
						this.T12021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22021 = null;
					} else {
						this.T22021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32021 = null;
					} else {
						this.T32021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42021 = null;
					} else {
						this.T42021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12022 = null;
					} else {
						this.T12022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22022 = null;
					} else {
						this.T22022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32022 = null;
					} else {
						this.T32022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42022 = null;
					} else {
						this.T42022 = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Nom_serie, dos);

				// String

				writeString(this.Zone_geographique, dos);

				// Double

				if (this.T42001 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42001);
				}

				// Double

				if (this.T12002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12002);
				}

				// Double

				if (this.T22002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22002);
				}

				// Double

				if (this.T32002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32002);
				}

				// Double

				if (this.T42002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42002);
				}

				// Double

				if (this.T12003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12003);
				}

				// Double

				if (this.T22003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22003);
				}

				// Double

				if (this.T32003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32003);
				}

				// Double

				if (this.T42003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42003);
				}

				// Double

				if (this.T12004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12004);
				}

				// Double

				if (this.T22004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22004);
				}

				// Double

				if (this.T32004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32004);
				}

				// Double

				if (this.T42004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42004);
				}

				// Double

				if (this.T12005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12005);
				}

				// Double

				if (this.T22005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22005);
				}

				// Double

				if (this.T32005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32005);
				}

				// Double

				if (this.T42005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42005);
				}

				// Double

				if (this.T12006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12006);
				}

				// Double

				if (this.T22006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22006);
				}

				// Double

				if (this.T32006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32006);
				}

				// Double

				if (this.T42006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42006);
				}

				// Double

				if (this.T12007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12007);
				}

				// Double

				if (this.T22007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22007);
				}

				// Double

				if (this.T32007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32007);
				}

				// Double

				if (this.T42007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42007);
				}

				// Double

				if (this.T12008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12008);
				}

				// Double

				if (this.T22008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22008);
				}

				// Double

				if (this.T32008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32008);
				}

				// Double

				if (this.T42008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42008);
				}

				// Double

				if (this.T12009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12009);
				}

				// Double

				if (this.T22009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22009);
				}

				// Double

				if (this.T32009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32009);
				}

				// Double

				if (this.T42009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42009);
				}

				// Double

				if (this.T12010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12010);
				}

				// Double

				if (this.T22010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22010);
				}

				// Double

				if (this.T32010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32010);
				}

				// Double

				if (this.T42010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42010);
				}

				// Double

				if (this.T12011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12011);
				}

				// Double

				if (this.T22011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22011);
				}

				// Double

				if (this.T32011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32011);
				}

				// Double

				if (this.T42011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42011);
				}

				// Double

				if (this.T12012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12012);
				}

				// Double

				if (this.T22012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22012);
				}

				// Double

				if (this.T32012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32012);
				}

				// Double

				if (this.T42012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42012);
				}

				// Double

				if (this.T12013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12013);
				}

				// Double

				if (this.T22013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22013);
				}

				// Double

				if (this.T32013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32013);
				}

				// Double

				if (this.T42013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42013);
				}

				// Double

				if (this.T12014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12014);
				}

				// Double

				if (this.T22014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22014);
				}

				// Double

				if (this.T32014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32014);
				}

				// Double

				if (this.T42014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42014);
				}

				// Double

				if (this.T12015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12015);
				}

				// Double

				if (this.T22015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22015);
				}

				// Double

				if (this.T32015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32015);
				}

				// Double

				if (this.T42015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42015);
				}

				// Double

				if (this.T12016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12016);
				}

				// Double

				if (this.T22016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22016);
				}

				// Double

				if (this.T32016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32016);
				}

				// Double

				if (this.T42016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42016);
				}

				// Double

				if (this.T12017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12017);
				}

				// Double

				if (this.T22017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22017);
				}

				// Double

				if (this.T32017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32017);
				}

				// Double

				if (this.T42017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42017);
				}

				// Double

				if (this.T12018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12018);
				}

				// Double

				if (this.T22018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22018);
				}

				// Double

				if (this.T32018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32018);
				}

				// Double

				if (this.T42018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42018);
				}

				// Double

				if (this.T12019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12019);
				}

				// Double

				if (this.T22019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22019);
				}

				// Double

				if (this.T32019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32019);
				}

				// Double

				if (this.T42019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42019);
				}

				// Double

				if (this.T12020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12020);
				}

				// Double

				if (this.T22020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22020);
				}

				// Double

				if (this.T32020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32020);
				}

				// Double

				if (this.T42020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42020);
				}

				// Double

				if (this.T12021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12021);
				}

				// Double

				if (this.T22021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22021);
				}

				// Double

				if (this.T32021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32021);
				}

				// Double

				if (this.T42021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42021);
				}

				// Double

				if (this.T12022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12022);
				}

				// Double

				if (this.T22022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22022);
				}

				// Double

				if (this.T32022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32022);
				}

				// Double

				if (this.T42022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42022);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Nom_serie, dos);

				// String

				writeString(this.Zone_geographique, dos);

				// Double

				if (this.T42001 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42001);
				}

				// Double

				if (this.T12002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12002);
				}

				// Double

				if (this.T22002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22002);
				}

				// Double

				if (this.T32002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32002);
				}

				// Double

				if (this.T42002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42002);
				}

				// Double

				if (this.T12003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12003);
				}

				// Double

				if (this.T22003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22003);
				}

				// Double

				if (this.T32003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32003);
				}

				// Double

				if (this.T42003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42003);
				}

				// Double

				if (this.T12004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12004);
				}

				// Double

				if (this.T22004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22004);
				}

				// Double

				if (this.T32004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32004);
				}

				// Double

				if (this.T42004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42004);
				}

				// Double

				if (this.T12005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12005);
				}

				// Double

				if (this.T22005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22005);
				}

				// Double

				if (this.T32005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32005);
				}

				// Double

				if (this.T42005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42005);
				}

				// Double

				if (this.T12006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12006);
				}

				// Double

				if (this.T22006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22006);
				}

				// Double

				if (this.T32006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32006);
				}

				// Double

				if (this.T42006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42006);
				}

				// Double

				if (this.T12007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12007);
				}

				// Double

				if (this.T22007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22007);
				}

				// Double

				if (this.T32007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32007);
				}

				// Double

				if (this.T42007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42007);
				}

				// Double

				if (this.T12008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12008);
				}

				// Double

				if (this.T22008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22008);
				}

				// Double

				if (this.T32008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32008);
				}

				// Double

				if (this.T42008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42008);
				}

				// Double

				if (this.T12009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12009);
				}

				// Double

				if (this.T22009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22009);
				}

				// Double

				if (this.T32009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32009);
				}

				// Double

				if (this.T42009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42009);
				}

				// Double

				if (this.T12010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12010);
				}

				// Double

				if (this.T22010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22010);
				}

				// Double

				if (this.T32010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32010);
				}

				// Double

				if (this.T42010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42010);
				}

				// Double

				if (this.T12011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12011);
				}

				// Double

				if (this.T22011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22011);
				}

				// Double

				if (this.T32011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32011);
				}

				// Double

				if (this.T42011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42011);
				}

				// Double

				if (this.T12012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12012);
				}

				// Double

				if (this.T22012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22012);
				}

				// Double

				if (this.T32012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32012);
				}

				// Double

				if (this.T42012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42012);
				}

				// Double

				if (this.T12013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12013);
				}

				// Double

				if (this.T22013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22013);
				}

				// Double

				if (this.T32013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32013);
				}

				// Double

				if (this.T42013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42013);
				}

				// Double

				if (this.T12014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12014);
				}

				// Double

				if (this.T22014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22014);
				}

				// Double

				if (this.T32014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32014);
				}

				// Double

				if (this.T42014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42014);
				}

				// Double

				if (this.T12015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12015);
				}

				// Double

				if (this.T22015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22015);
				}

				// Double

				if (this.T32015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32015);
				}

				// Double

				if (this.T42015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42015);
				}

				// Double

				if (this.T12016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12016);
				}

				// Double

				if (this.T22016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22016);
				}

				// Double

				if (this.T32016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32016);
				}

				// Double

				if (this.T42016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42016);
				}

				// Double

				if (this.T12017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12017);
				}

				// Double

				if (this.T22017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22017);
				}

				// Double

				if (this.T32017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32017);
				}

				// Double

				if (this.T42017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42017);
				}

				// Double

				if (this.T12018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12018);
				}

				// Double

				if (this.T22018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22018);
				}

				// Double

				if (this.T32018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32018);
				}

				// Double

				if (this.T42018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42018);
				}

				// Double

				if (this.T12019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12019);
				}

				// Double

				if (this.T22019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22019);
				}

				// Double

				if (this.T32019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32019);
				}

				// Double

				if (this.T42019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42019);
				}

				// Double

				if (this.T12020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12020);
				}

				// Double

				if (this.T22020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22020);
				}

				// Double

				if (this.T32020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32020);
				}

				// Double

				if (this.T42020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42020);
				}

				// Double

				if (this.T12021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12021);
				}

				// Double

				if (this.T22021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22021);
				}

				// Double

				if (this.T32021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32021);
				}

				// Double

				if (this.T42021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42021);
				}

				// Double

				if (this.T12022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12022);
				}

				// Double

				if (this.T22022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22022);
				}

				// Double

				if (this.T32022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32022);
				}

				// Double

				if (this.T42022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42022);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Nom_serie=" + Nom_serie);
			sb.append(",Zone_geographique=" + Zone_geographique);
			sb.append(",T42001=" + String.valueOf(T42001));
			sb.append(",T12002=" + String.valueOf(T12002));
			sb.append(",T22002=" + String.valueOf(T22002));
			sb.append(",T32002=" + String.valueOf(T32002));
			sb.append(",T42002=" + String.valueOf(T42002));
			sb.append(",T12003=" + String.valueOf(T12003));
			sb.append(",T22003=" + String.valueOf(T22003));
			sb.append(",T32003=" + String.valueOf(T32003));
			sb.append(",T42003=" + String.valueOf(T42003));
			sb.append(",T12004=" + String.valueOf(T12004));
			sb.append(",T22004=" + String.valueOf(T22004));
			sb.append(",T32004=" + String.valueOf(T32004));
			sb.append(",T42004=" + String.valueOf(T42004));
			sb.append(",T12005=" + String.valueOf(T12005));
			sb.append(",T22005=" + String.valueOf(T22005));
			sb.append(",T32005=" + String.valueOf(T32005));
			sb.append(",T42005=" + String.valueOf(T42005));
			sb.append(",T12006=" + String.valueOf(T12006));
			sb.append(",T22006=" + String.valueOf(T22006));
			sb.append(",T32006=" + String.valueOf(T32006));
			sb.append(",T42006=" + String.valueOf(T42006));
			sb.append(",T12007=" + String.valueOf(T12007));
			sb.append(",T22007=" + String.valueOf(T22007));
			sb.append(",T32007=" + String.valueOf(T32007));
			sb.append(",T42007=" + String.valueOf(T42007));
			sb.append(",T12008=" + String.valueOf(T12008));
			sb.append(",T22008=" + String.valueOf(T22008));
			sb.append(",T32008=" + String.valueOf(T32008));
			sb.append(",T42008=" + String.valueOf(T42008));
			sb.append(",T12009=" + String.valueOf(T12009));
			sb.append(",T22009=" + String.valueOf(T22009));
			sb.append(",T32009=" + String.valueOf(T32009));
			sb.append(",T42009=" + String.valueOf(T42009));
			sb.append(",T12010=" + String.valueOf(T12010));
			sb.append(",T22010=" + String.valueOf(T22010));
			sb.append(",T32010=" + String.valueOf(T32010));
			sb.append(",T42010=" + String.valueOf(T42010));
			sb.append(",T12011=" + String.valueOf(T12011));
			sb.append(",T22011=" + String.valueOf(T22011));
			sb.append(",T32011=" + String.valueOf(T32011));
			sb.append(",T42011=" + String.valueOf(T42011));
			sb.append(",T12012=" + String.valueOf(T12012));
			sb.append(",T22012=" + String.valueOf(T22012));
			sb.append(",T32012=" + String.valueOf(T32012));
			sb.append(",T42012=" + String.valueOf(T42012));
			sb.append(",T12013=" + String.valueOf(T12013));
			sb.append(",T22013=" + String.valueOf(T22013));
			sb.append(",T32013=" + String.valueOf(T32013));
			sb.append(",T42013=" + String.valueOf(T42013));
			sb.append(",T12014=" + String.valueOf(T12014));
			sb.append(",T22014=" + String.valueOf(T22014));
			sb.append(",T32014=" + String.valueOf(T32014));
			sb.append(",T42014=" + String.valueOf(T42014));
			sb.append(",T12015=" + String.valueOf(T12015));
			sb.append(",T22015=" + String.valueOf(T22015));
			sb.append(",T32015=" + String.valueOf(T32015));
			sb.append(",T42015=" + String.valueOf(T42015));
			sb.append(",T12016=" + String.valueOf(T12016));
			sb.append(",T22016=" + String.valueOf(T22016));
			sb.append(",T32016=" + String.valueOf(T32016));
			sb.append(",T42016=" + String.valueOf(T42016));
			sb.append(",T12017=" + String.valueOf(T12017));
			sb.append(",T22017=" + String.valueOf(T22017));
			sb.append(",T32017=" + String.valueOf(T32017));
			sb.append(",T42017=" + String.valueOf(T42017));
			sb.append(",T12018=" + String.valueOf(T12018));
			sb.append(",T22018=" + String.valueOf(T22018));
			sb.append(",T32018=" + String.valueOf(T32018));
			sb.append(",T42018=" + String.valueOf(T42018));
			sb.append(",T12019=" + String.valueOf(T12019));
			sb.append(",T22019=" + String.valueOf(T22019));
			sb.append(",T32019=" + String.valueOf(T32019));
			sb.append(",T42019=" + String.valueOf(T42019));
			sb.append(",T12020=" + String.valueOf(T12020));
			sb.append(",T22020=" + String.valueOf(T22020));
			sb.append(",T32020=" + String.valueOf(T32020));
			sb.append(",T42020=" + String.valueOf(T42020));
			sb.append(",T12021=" + String.valueOf(T12021));
			sb.append(",T22021=" + String.valueOf(T22021));
			sb.append(",T32021=" + String.valueOf(T32021));
			sb.append(",T42021=" + String.valueOf(T42021));
			sb.append(",T12022=" + String.valueOf(T12022));
			sb.append(",T22022=" + String.valueOf(T22022));
			sb.append(",T32022=" + String.valueOf(T32022));
			sb.append(",T42022=" + String.valueOf(T42022));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(Donnees_mergeesStruct other) {

			int returnValue = -1;

			returnValue = checkNullsAndCompare(this.Nom_serie, other.Nom_serie);
			if (returnValue != 0) {
				return returnValue;
			}

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[0];
		static byte[] commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[0];

		public String Zone_geographique;

		public String getZone_geographique() {
			return this.Zone_geographique;
		}

		public Double T42001;

		public Double getT42001() {
			return this.T42001;
		}

		public Double T12002;

		public Double getT12002() {
			return this.T12002;
		}

		public Double T22002;

		public Double getT22002() {
			return this.T22002;
		}

		public Double T32002;

		public Double getT32002() {
			return this.T32002;
		}

		public Double T42002;

		public Double getT42002() {
			return this.T42002;
		}

		public Double T12003;

		public Double getT12003() {
			return this.T12003;
		}

		public Double T22003;

		public Double getT22003() {
			return this.T22003;
		}

		public Double T32003;

		public Double getT32003() {
			return this.T32003;
		}

		public Double T42003;

		public Double getT42003() {
			return this.T42003;
		}

		public Double T12004;

		public Double getT12004() {
			return this.T12004;
		}

		public Double T22004;

		public Double getT22004() {
			return this.T22004;
		}

		public Double T32004;

		public Double getT32004() {
			return this.T32004;
		}

		public Double T42004;

		public Double getT42004() {
			return this.T42004;
		}

		public Double T12005;

		public Double getT12005() {
			return this.T12005;
		}

		public Double T22005;

		public Double getT22005() {
			return this.T22005;
		}

		public Double T32005;

		public Double getT32005() {
			return this.T32005;
		}

		public Double T42005;

		public Double getT42005() {
			return this.T42005;
		}

		public Double T12006;

		public Double getT12006() {
			return this.T12006;
		}

		public Double T22006;

		public Double getT22006() {
			return this.T22006;
		}

		public Double T32006;

		public Double getT32006() {
			return this.T32006;
		}

		public Double T42006;

		public Double getT42006() {
			return this.T42006;
		}

		public Double T12007;

		public Double getT12007() {
			return this.T12007;
		}

		public Double T22007;

		public Double getT22007() {
			return this.T22007;
		}

		public Double T32007;

		public Double getT32007() {
			return this.T32007;
		}

		public Double T42007;

		public Double getT42007() {
			return this.T42007;
		}

		public Double T12008;

		public Double getT12008() {
			return this.T12008;
		}

		public Double T22008;

		public Double getT22008() {
			return this.T22008;
		}

		public Double T32008;

		public Double getT32008() {
			return this.T32008;
		}

		public Double T42008;

		public Double getT42008() {
			return this.T42008;
		}

		public Double T12009;

		public Double getT12009() {
			return this.T12009;
		}

		public Double T22009;

		public Double getT22009() {
			return this.T22009;
		}

		public Double T32009;

		public Double getT32009() {
			return this.T32009;
		}

		public Double T42009;

		public Double getT42009() {
			return this.T42009;
		}

		public Double T12010;

		public Double getT12010() {
			return this.T12010;
		}

		public Double T22010;

		public Double getT22010() {
			return this.T22010;
		}

		public Double T32010;

		public Double getT32010() {
			return this.T32010;
		}

		public Double T42010;

		public Double getT42010() {
			return this.T42010;
		}

		public Double T12011;

		public Double getT12011() {
			return this.T12011;
		}

		public Double T22011;

		public Double getT22011() {
			return this.T22011;
		}

		public Double T32011;

		public Double getT32011() {
			return this.T32011;
		}

		public Double T42011;

		public Double getT42011() {
			return this.T42011;
		}

		public Double T12012;

		public Double getT12012() {
			return this.T12012;
		}

		public Double T22012;

		public Double getT22012() {
			return this.T22012;
		}

		public Double T32012;

		public Double getT32012() {
			return this.T32012;
		}

		public Double T42012;

		public Double getT42012() {
			return this.T42012;
		}

		public Double T12013;

		public Double getT12013() {
			return this.T12013;
		}

		public Double T22013;

		public Double getT22013() {
			return this.T22013;
		}

		public Double T32013;

		public Double getT32013() {
			return this.T32013;
		}

		public Double T42013;

		public Double getT42013() {
			return this.T42013;
		}

		public Double T12014;

		public Double getT12014() {
			return this.T12014;
		}

		public Double T22014;

		public Double getT22014() {
			return this.T22014;
		}

		public Double T32014;

		public Double getT32014() {
			return this.T32014;
		}

		public Double T42014;

		public Double getT42014() {
			return this.T42014;
		}

		public Double T12015;

		public Double getT12015() {
			return this.T12015;
		}

		public Double T22015;

		public Double getT22015() {
			return this.T22015;
		}

		public Double T32015;

		public Double getT32015() {
			return this.T32015;
		}

		public Double T42015;

		public Double getT42015() {
			return this.T42015;
		}

		public Double T12016;

		public Double getT12016() {
			return this.T12016;
		}

		public Double T22016;

		public Double getT22016() {
			return this.T22016;
		}

		public Double T32016;

		public Double getT32016() {
			return this.T32016;
		}

		public Double T42016;

		public Double getT42016() {
			return this.T42016;
		}

		public Double T12017;

		public Double getT12017() {
			return this.T12017;
		}

		public Double T22017;

		public Double getT22017() {
			return this.T22017;
		}

		public Double T32017;

		public Double getT32017() {
			return this.T32017;
		}

		public Double T42017;

		public Double getT42017() {
			return this.T42017;
		}

		public Double T12018;

		public Double getT12018() {
			return this.T12018;
		}

		public Double T22018;

		public Double getT22018() {
			return this.T22018;
		}

		public Double T32018;

		public Double getT32018() {
			return this.T32018;
		}

		public Double T42018;

		public Double getT42018() {
			return this.T42018;
		}

		public Double T12019;

		public Double getT12019() {
			return this.T12019;
		}

		public Double T22019;

		public Double getT22019() {
			return this.T22019;
		}

		public Double T32019;

		public Double getT32019() {
			return this.T32019;
		}

		public Double T42019;

		public Double getT42019() {
			return this.T42019;
		}

		public Double T12020;

		public Double getT12020() {
			return this.T12020;
		}

		public Double T22020;

		public Double getT22020() {
			return this.T22020;
		}

		public Double T32020;

		public Double getT32020() {
			return this.T32020;
		}

		public Double T42020;

		public Double getT42020() {
			return this.T42020;
		}

		public Double T12021;

		public Double getT12021() {
			return this.T12021;
		}

		public Double T22021;

		public Double getT22021() {
			return this.T22021;
		}

		public Double T32021;

		public Double getT32021() {
			return this.T32021;
		}

		public Double T42021;

		public Double getT42021() {
			return this.T42021;
		}

		public Double T12022;

		public Double getT12022() {
			return this.T12022;
		}

		public Double T22022;

		public Double getT22022() {
			return this.T22022;
		}

		public Double T32022;

		public Double getT32022() {
			return this.T32022;
		}

		public Double T42022;

		public Double getT42022() {
			return this.T42022;
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi.length) {
					if (length < 1024 && commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi.length == 0) {
						commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[1024];
					} else {
						commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi, 0, length);
				strReturn = new String(commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi.length) {
					if (length < 1024 && commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi.length == 0) {
						commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[1024];
					} else {
						commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi, 0, length);
				strReturn = new String(commonByteArray_MSPR_BIG_DATA_Concatenation_fichier_emploi, 0, length,
						utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_MSPR_BIG_DATA_Concatenation_fichier_emploi) {

				try {

					int length = 0;

					this.Zone_geographique = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.T42001 = null;
					} else {
						this.T42001 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12002 = null;
					} else {
						this.T12002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22002 = null;
					} else {
						this.T22002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32002 = null;
					} else {
						this.T32002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42002 = null;
					} else {
						this.T42002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12003 = null;
					} else {
						this.T12003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22003 = null;
					} else {
						this.T22003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32003 = null;
					} else {
						this.T32003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42003 = null;
					} else {
						this.T42003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12004 = null;
					} else {
						this.T12004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22004 = null;
					} else {
						this.T22004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32004 = null;
					} else {
						this.T32004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42004 = null;
					} else {
						this.T42004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12005 = null;
					} else {
						this.T12005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22005 = null;
					} else {
						this.T22005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32005 = null;
					} else {
						this.T32005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42005 = null;
					} else {
						this.T42005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12006 = null;
					} else {
						this.T12006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22006 = null;
					} else {
						this.T22006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32006 = null;
					} else {
						this.T32006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42006 = null;
					} else {
						this.T42006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12007 = null;
					} else {
						this.T12007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22007 = null;
					} else {
						this.T22007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32007 = null;
					} else {
						this.T32007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42007 = null;
					} else {
						this.T42007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12008 = null;
					} else {
						this.T12008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22008 = null;
					} else {
						this.T22008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32008 = null;
					} else {
						this.T32008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42008 = null;
					} else {
						this.T42008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12009 = null;
					} else {
						this.T12009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22009 = null;
					} else {
						this.T22009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32009 = null;
					} else {
						this.T32009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42009 = null;
					} else {
						this.T42009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12010 = null;
					} else {
						this.T12010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22010 = null;
					} else {
						this.T22010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32010 = null;
					} else {
						this.T32010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42010 = null;
					} else {
						this.T42010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12011 = null;
					} else {
						this.T12011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22011 = null;
					} else {
						this.T22011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32011 = null;
					} else {
						this.T32011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42011 = null;
					} else {
						this.T42011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12012 = null;
					} else {
						this.T12012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22012 = null;
					} else {
						this.T22012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32012 = null;
					} else {
						this.T32012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42012 = null;
					} else {
						this.T42012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12013 = null;
					} else {
						this.T12013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22013 = null;
					} else {
						this.T22013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32013 = null;
					} else {
						this.T32013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42013 = null;
					} else {
						this.T42013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12014 = null;
					} else {
						this.T12014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22014 = null;
					} else {
						this.T22014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32014 = null;
					} else {
						this.T32014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42014 = null;
					} else {
						this.T42014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12015 = null;
					} else {
						this.T12015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22015 = null;
					} else {
						this.T22015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32015 = null;
					} else {
						this.T32015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42015 = null;
					} else {
						this.T42015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12016 = null;
					} else {
						this.T12016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22016 = null;
					} else {
						this.T22016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32016 = null;
					} else {
						this.T32016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42016 = null;
					} else {
						this.T42016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12017 = null;
					} else {
						this.T12017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22017 = null;
					} else {
						this.T22017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32017 = null;
					} else {
						this.T32017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42017 = null;
					} else {
						this.T42017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12018 = null;
					} else {
						this.T12018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22018 = null;
					} else {
						this.T22018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32018 = null;
					} else {
						this.T32018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42018 = null;
					} else {
						this.T42018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12019 = null;
					} else {
						this.T12019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22019 = null;
					} else {
						this.T22019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32019 = null;
					} else {
						this.T32019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42019 = null;
					} else {
						this.T42019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12020 = null;
					} else {
						this.T12020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22020 = null;
					} else {
						this.T22020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32020 = null;
					} else {
						this.T32020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42020 = null;
					} else {
						this.T42020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12021 = null;
					} else {
						this.T12021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22021 = null;
					} else {
						this.T22021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32021 = null;
					} else {
						this.T32021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42021 = null;
					} else {
						this.T42021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12022 = null;
					} else {
						this.T12022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22022 = null;
					} else {
						this.T22022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32022 = null;
					} else {
						this.T32022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42022 = null;
					} else {
						this.T42022 = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_MSPR_BIG_DATA_Concatenation_fichier_emploi) {

				try {

					int length = 0;

					this.Zone_geographique = readString(dis);

					length = dis.readByte();
					if (length == -1) {
						this.T42001 = null;
					} else {
						this.T42001 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12002 = null;
					} else {
						this.T12002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22002 = null;
					} else {
						this.T22002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32002 = null;
					} else {
						this.T32002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42002 = null;
					} else {
						this.T42002 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12003 = null;
					} else {
						this.T12003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22003 = null;
					} else {
						this.T22003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32003 = null;
					} else {
						this.T32003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42003 = null;
					} else {
						this.T42003 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12004 = null;
					} else {
						this.T12004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22004 = null;
					} else {
						this.T22004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32004 = null;
					} else {
						this.T32004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42004 = null;
					} else {
						this.T42004 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12005 = null;
					} else {
						this.T12005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22005 = null;
					} else {
						this.T22005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32005 = null;
					} else {
						this.T32005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42005 = null;
					} else {
						this.T42005 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12006 = null;
					} else {
						this.T12006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22006 = null;
					} else {
						this.T22006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32006 = null;
					} else {
						this.T32006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42006 = null;
					} else {
						this.T42006 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12007 = null;
					} else {
						this.T12007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22007 = null;
					} else {
						this.T22007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32007 = null;
					} else {
						this.T32007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42007 = null;
					} else {
						this.T42007 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12008 = null;
					} else {
						this.T12008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22008 = null;
					} else {
						this.T22008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32008 = null;
					} else {
						this.T32008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42008 = null;
					} else {
						this.T42008 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12009 = null;
					} else {
						this.T12009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22009 = null;
					} else {
						this.T22009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32009 = null;
					} else {
						this.T32009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42009 = null;
					} else {
						this.T42009 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12010 = null;
					} else {
						this.T12010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22010 = null;
					} else {
						this.T22010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32010 = null;
					} else {
						this.T32010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42010 = null;
					} else {
						this.T42010 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12011 = null;
					} else {
						this.T12011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22011 = null;
					} else {
						this.T22011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32011 = null;
					} else {
						this.T32011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42011 = null;
					} else {
						this.T42011 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12012 = null;
					} else {
						this.T12012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22012 = null;
					} else {
						this.T22012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32012 = null;
					} else {
						this.T32012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42012 = null;
					} else {
						this.T42012 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12013 = null;
					} else {
						this.T12013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22013 = null;
					} else {
						this.T22013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32013 = null;
					} else {
						this.T32013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42013 = null;
					} else {
						this.T42013 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12014 = null;
					} else {
						this.T12014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22014 = null;
					} else {
						this.T22014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32014 = null;
					} else {
						this.T32014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42014 = null;
					} else {
						this.T42014 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12015 = null;
					} else {
						this.T12015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22015 = null;
					} else {
						this.T22015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32015 = null;
					} else {
						this.T32015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42015 = null;
					} else {
						this.T42015 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12016 = null;
					} else {
						this.T12016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22016 = null;
					} else {
						this.T22016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32016 = null;
					} else {
						this.T32016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42016 = null;
					} else {
						this.T42016 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12017 = null;
					} else {
						this.T12017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22017 = null;
					} else {
						this.T22017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32017 = null;
					} else {
						this.T32017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42017 = null;
					} else {
						this.T42017 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12018 = null;
					} else {
						this.T12018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22018 = null;
					} else {
						this.T22018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32018 = null;
					} else {
						this.T32018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42018 = null;
					} else {
						this.T42018 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12019 = null;
					} else {
						this.T12019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22019 = null;
					} else {
						this.T22019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32019 = null;
					} else {
						this.T32019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42019 = null;
					} else {
						this.T42019 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12020 = null;
					} else {
						this.T12020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22020 = null;
					} else {
						this.T22020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32020 = null;
					} else {
						this.T32020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42020 = null;
					} else {
						this.T42020 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12021 = null;
					} else {
						this.T12021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22021 = null;
					} else {
						this.T22021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32021 = null;
					} else {
						this.T32021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42021 = null;
					} else {
						this.T42021 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T12022 = null;
					} else {
						this.T12022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T22022 = null;
					} else {
						this.T22022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T32022 = null;
					} else {
						this.T32022 = dis.readDouble();
					}

					length = dis.readByte();
					if (length == -1) {
						this.T42022 = null;
					} else {
						this.T42022 = dis.readDouble();
					}

				} catch (IOException e) {
					throw new RuntimeException(e);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// String

				writeString(this.Zone_geographique, dos);

				// Double

				if (this.T42001 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42001);
				}

				// Double

				if (this.T12002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12002);
				}

				// Double

				if (this.T22002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22002);
				}

				// Double

				if (this.T32002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32002);
				}

				// Double

				if (this.T42002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42002);
				}

				// Double

				if (this.T12003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12003);
				}

				// Double

				if (this.T22003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22003);
				}

				// Double

				if (this.T32003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32003);
				}

				// Double

				if (this.T42003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42003);
				}

				// Double

				if (this.T12004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12004);
				}

				// Double

				if (this.T22004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22004);
				}

				// Double

				if (this.T32004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32004);
				}

				// Double

				if (this.T42004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42004);
				}

				// Double

				if (this.T12005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12005);
				}

				// Double

				if (this.T22005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22005);
				}

				// Double

				if (this.T32005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32005);
				}

				// Double

				if (this.T42005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42005);
				}

				// Double

				if (this.T12006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12006);
				}

				// Double

				if (this.T22006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22006);
				}

				// Double

				if (this.T32006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32006);
				}

				// Double

				if (this.T42006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42006);
				}

				// Double

				if (this.T12007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12007);
				}

				// Double

				if (this.T22007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22007);
				}

				// Double

				if (this.T32007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32007);
				}

				// Double

				if (this.T42007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42007);
				}

				// Double

				if (this.T12008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12008);
				}

				// Double

				if (this.T22008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22008);
				}

				// Double

				if (this.T32008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32008);
				}

				// Double

				if (this.T42008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42008);
				}

				// Double

				if (this.T12009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12009);
				}

				// Double

				if (this.T22009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22009);
				}

				// Double

				if (this.T32009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32009);
				}

				// Double

				if (this.T42009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42009);
				}

				// Double

				if (this.T12010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12010);
				}

				// Double

				if (this.T22010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22010);
				}

				// Double

				if (this.T32010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32010);
				}

				// Double

				if (this.T42010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42010);
				}

				// Double

				if (this.T12011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12011);
				}

				// Double

				if (this.T22011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22011);
				}

				// Double

				if (this.T32011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32011);
				}

				// Double

				if (this.T42011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42011);
				}

				// Double

				if (this.T12012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12012);
				}

				// Double

				if (this.T22012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22012);
				}

				// Double

				if (this.T32012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32012);
				}

				// Double

				if (this.T42012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42012);
				}

				// Double

				if (this.T12013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12013);
				}

				// Double

				if (this.T22013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22013);
				}

				// Double

				if (this.T32013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32013);
				}

				// Double

				if (this.T42013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42013);
				}

				// Double

				if (this.T12014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12014);
				}

				// Double

				if (this.T22014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22014);
				}

				// Double

				if (this.T32014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32014);
				}

				// Double

				if (this.T42014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42014);
				}

				// Double

				if (this.T12015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12015);
				}

				// Double

				if (this.T22015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22015);
				}

				// Double

				if (this.T32015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32015);
				}

				// Double

				if (this.T42015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42015);
				}

				// Double

				if (this.T12016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12016);
				}

				// Double

				if (this.T22016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22016);
				}

				// Double

				if (this.T32016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32016);
				}

				// Double

				if (this.T42016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42016);
				}

				// Double

				if (this.T12017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12017);
				}

				// Double

				if (this.T22017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22017);
				}

				// Double

				if (this.T32017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32017);
				}

				// Double

				if (this.T42017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42017);
				}

				// Double

				if (this.T12018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12018);
				}

				// Double

				if (this.T22018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22018);
				}

				// Double

				if (this.T32018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32018);
				}

				// Double

				if (this.T42018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42018);
				}

				// Double

				if (this.T12019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12019);
				}

				// Double

				if (this.T22019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22019);
				}

				// Double

				if (this.T32019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32019);
				}

				// Double

				if (this.T42019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42019);
				}

				// Double

				if (this.T12020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12020);
				}

				// Double

				if (this.T22020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22020);
				}

				// Double

				if (this.T32020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32020);
				}

				// Double

				if (this.T42020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42020);
				}

				// Double

				if (this.T12021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12021);
				}

				// Double

				if (this.T22021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22021);
				}

				// Double

				if (this.T32021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32021);
				}

				// Double

				if (this.T42021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42021);
				}

				// Double

				if (this.T12022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12022);
				}

				// Double

				if (this.T22022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22022);
				}

				// Double

				if (this.T32022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32022);
				}

				// Double

				if (this.T42022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42022);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// String

				writeString(this.Zone_geographique, dos);

				// Double

				if (this.T42001 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42001);
				}

				// Double

				if (this.T12002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12002);
				}

				// Double

				if (this.T22002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22002);
				}

				// Double

				if (this.T32002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32002);
				}

				// Double

				if (this.T42002 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42002);
				}

				// Double

				if (this.T12003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12003);
				}

				// Double

				if (this.T22003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22003);
				}

				// Double

				if (this.T32003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32003);
				}

				// Double

				if (this.T42003 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42003);
				}

				// Double

				if (this.T12004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12004);
				}

				// Double

				if (this.T22004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22004);
				}

				// Double

				if (this.T32004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32004);
				}

				// Double

				if (this.T42004 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42004);
				}

				// Double

				if (this.T12005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12005);
				}

				// Double

				if (this.T22005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22005);
				}

				// Double

				if (this.T32005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32005);
				}

				// Double

				if (this.T42005 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42005);
				}

				// Double

				if (this.T12006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12006);
				}

				// Double

				if (this.T22006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22006);
				}

				// Double

				if (this.T32006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32006);
				}

				// Double

				if (this.T42006 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42006);
				}

				// Double

				if (this.T12007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12007);
				}

				// Double

				if (this.T22007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22007);
				}

				// Double

				if (this.T32007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32007);
				}

				// Double

				if (this.T42007 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42007);
				}

				// Double

				if (this.T12008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12008);
				}

				// Double

				if (this.T22008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22008);
				}

				// Double

				if (this.T32008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32008);
				}

				// Double

				if (this.T42008 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42008);
				}

				// Double

				if (this.T12009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12009);
				}

				// Double

				if (this.T22009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22009);
				}

				// Double

				if (this.T32009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32009);
				}

				// Double

				if (this.T42009 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42009);
				}

				// Double

				if (this.T12010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12010);
				}

				// Double

				if (this.T22010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22010);
				}

				// Double

				if (this.T32010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32010);
				}

				// Double

				if (this.T42010 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42010);
				}

				// Double

				if (this.T12011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12011);
				}

				// Double

				if (this.T22011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22011);
				}

				// Double

				if (this.T32011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32011);
				}

				// Double

				if (this.T42011 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42011);
				}

				// Double

				if (this.T12012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12012);
				}

				// Double

				if (this.T22012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22012);
				}

				// Double

				if (this.T32012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32012);
				}

				// Double

				if (this.T42012 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42012);
				}

				// Double

				if (this.T12013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12013);
				}

				// Double

				if (this.T22013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22013);
				}

				// Double

				if (this.T32013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32013);
				}

				// Double

				if (this.T42013 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42013);
				}

				// Double

				if (this.T12014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12014);
				}

				// Double

				if (this.T22014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22014);
				}

				// Double

				if (this.T32014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32014);
				}

				// Double

				if (this.T42014 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42014);
				}

				// Double

				if (this.T12015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12015);
				}

				// Double

				if (this.T22015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22015);
				}

				// Double

				if (this.T32015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32015);
				}

				// Double

				if (this.T42015 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42015);
				}

				// Double

				if (this.T12016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12016);
				}

				// Double

				if (this.T22016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22016);
				}

				// Double

				if (this.T32016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32016);
				}

				// Double

				if (this.T42016 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42016);
				}

				// Double

				if (this.T12017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12017);
				}

				// Double

				if (this.T22017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22017);
				}

				// Double

				if (this.T32017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32017);
				}

				// Double

				if (this.T42017 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42017);
				}

				// Double

				if (this.T12018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12018);
				}

				// Double

				if (this.T22018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22018);
				}

				// Double

				if (this.T32018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32018);
				}

				// Double

				if (this.T42018 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42018);
				}

				// Double

				if (this.T12019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12019);
				}

				// Double

				if (this.T22019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22019);
				}

				// Double

				if (this.T32019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32019);
				}

				// Double

				if (this.T42019 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42019);
				}

				// Double

				if (this.T12020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12020);
				}

				// Double

				if (this.T22020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22020);
				}

				// Double

				if (this.T32020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32020);
				}

				// Double

				if (this.T42020 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42020);
				}

				// Double

				if (this.T12021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12021);
				}

				// Double

				if (this.T22021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22021);
				}

				// Double

				if (this.T32021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32021);
				}

				// Double

				if (this.T42021 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42021);
				}

				// Double

				if (this.T12022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T12022);
				}

				// Double

				if (this.T22022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T22022);
				}

				// Double

				if (this.T32022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T32022);
				}

				// Double

				if (this.T42022 == null) {
					dos.writeByte(-1);
				} else {
					dos.writeByte(0);
					dos.writeDouble(this.T42022);
				}

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("Zone_geographique=" + Zone_geographique);
			sb.append(",T42001=" + String.valueOf(T42001));
			sb.append(",T12002=" + String.valueOf(T12002));
			sb.append(",T22002=" + String.valueOf(T22002));
			sb.append(",T32002=" + String.valueOf(T32002));
			sb.append(",T42002=" + String.valueOf(T42002));
			sb.append(",T12003=" + String.valueOf(T12003));
			sb.append(",T22003=" + String.valueOf(T22003));
			sb.append(",T32003=" + String.valueOf(T32003));
			sb.append(",T42003=" + String.valueOf(T42003));
			sb.append(",T12004=" + String.valueOf(T12004));
			sb.append(",T22004=" + String.valueOf(T22004));
			sb.append(",T32004=" + String.valueOf(T32004));
			sb.append(",T42004=" + String.valueOf(T42004));
			sb.append(",T12005=" + String.valueOf(T12005));
			sb.append(",T22005=" + String.valueOf(T22005));
			sb.append(",T32005=" + String.valueOf(T32005));
			sb.append(",T42005=" + String.valueOf(T42005));
			sb.append(",T12006=" + String.valueOf(T12006));
			sb.append(",T22006=" + String.valueOf(T22006));
			sb.append(",T32006=" + String.valueOf(T32006));
			sb.append(",T42006=" + String.valueOf(T42006));
			sb.append(",T12007=" + String.valueOf(T12007));
			sb.append(",T22007=" + String.valueOf(T22007));
			sb.append(",T32007=" + String.valueOf(T32007));
			sb.append(",T42007=" + String.valueOf(T42007));
			sb.append(",T12008=" + String.valueOf(T12008));
			sb.append(",T22008=" + String.valueOf(T22008));
			sb.append(",T32008=" + String.valueOf(T32008));
			sb.append(",T42008=" + String.valueOf(T42008));
			sb.append(",T12009=" + String.valueOf(T12009));
			sb.append(",T22009=" + String.valueOf(T22009));
			sb.append(",T32009=" + String.valueOf(T32009));
			sb.append(",T42009=" + String.valueOf(T42009));
			sb.append(",T12010=" + String.valueOf(T12010));
			sb.append(",T22010=" + String.valueOf(T22010));
			sb.append(",T32010=" + String.valueOf(T32010));
			sb.append(",T42010=" + String.valueOf(T42010));
			sb.append(",T12011=" + String.valueOf(T12011));
			sb.append(",T22011=" + String.valueOf(T22011));
			sb.append(",T32011=" + String.valueOf(T32011));
			sb.append(",T42011=" + String.valueOf(T42011));
			sb.append(",T12012=" + String.valueOf(T12012));
			sb.append(",T22012=" + String.valueOf(T22012));
			sb.append(",T32012=" + String.valueOf(T32012));
			sb.append(",T42012=" + String.valueOf(T42012));
			sb.append(",T12013=" + String.valueOf(T12013));
			sb.append(",T22013=" + String.valueOf(T22013));
			sb.append(",T32013=" + String.valueOf(T32013));
			sb.append(",T42013=" + String.valueOf(T42013));
			sb.append(",T12014=" + String.valueOf(T12014));
			sb.append(",T22014=" + String.valueOf(T22014));
			sb.append(",T32014=" + String.valueOf(T32014));
			sb.append(",T42014=" + String.valueOf(T42014));
			sb.append(",T12015=" + String.valueOf(T12015));
			sb.append(",T22015=" + String.valueOf(T22015));
			sb.append(",T32015=" + String.valueOf(T32015));
			sb.append(",T42015=" + String.valueOf(T42015));
			sb.append(",T12016=" + String.valueOf(T12016));
			sb.append(",T22016=" + String.valueOf(T22016));
			sb.append(",T32016=" + String.valueOf(T32016));
			sb.append(",T42016=" + String.valueOf(T42016));
			sb.append(",T12017=" + String.valueOf(T12017));
			sb.append(",T22017=" + String.valueOf(T22017));
			sb.append(",T32017=" + String.valueOf(T32017));
			sb.append(",T42017=" + String.valueOf(T42017));
			sb.append(",T12018=" + String.valueOf(T12018));
			sb.append(",T22018=" + String.valueOf(T22018));
			sb.append(",T32018=" + String.valueOf(T32018));
			sb.append(",T42018=" + String.valueOf(T42018));
			sb.append(",T12019=" + String.valueOf(T12019));
			sb.append(",T22019=" + String.valueOf(T22019));
			sb.append(",T32019=" + String.valueOf(T32019));
			sb.append(",T42019=" + String.valueOf(T42019));
			sb.append(",T12020=" + String.valueOf(T12020));
			sb.append(",T22020=" + String.valueOf(T22020));
			sb.append(",T32020=" + String.valueOf(T32020));
			sb.append(",T42020=" + String.valueOf(T42020));
			sb.append(",T12021=" + String.valueOf(T12021));
			sb.append(",T22021=" + String.valueOf(T22021));
			sb.append(",T32021=" + String.valueOf(T32021));
			sb.append(",T42021=" + String.valueOf(T42021));
			sb.append(",T12022=" + String.valueOf(T12022));
			sb.append(",T22022=" + String.valueOf(T22022));
			sb.append(",T32022=" + String.valueOf(T32022));
			sb.append(",T42022=" + String.valueOf(T42022));
			sb.append("]");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tFileInputExcel_1Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();
				Donnees_mergeesStruct Donnees_mergees = new Donnees_mergeesStruct();

				/**
				 * [tFileOutputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileOutputExcel_1", false);
				start_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileOutputExcel_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "Donnees_mergees");
				}

				int tos_count_tFileOutputExcel_1 = 0;

				int columnIndex_tFileOutputExcel_1 = 0;
				boolean headerIsInserted_tFileOutputExcel_1 = false;

				String fileName_tFileOutputExcel_1 = "D:\\Alexandre\\Dev\\i1-mspr-data\\data\\donnees_emploi_mergees.xlsx";
				int nb_line_tFileOutputExcel_1 = 0;
				org.talend.ExcelTool xlsxTool_tFileOutputExcel_1 = new org.talend.ExcelTool();

				xlsxTool_tFileOutputExcel_1.setTruncateExceedingCharacters(false);
				xlsxTool_tFileOutputExcel_1.setSheet("Sheet1");
				xlsxTool_tFileOutputExcel_1.setAppend(false, false, false);
				xlsxTool_tFileOutputExcel_1.setRecalculateFormula(false);
				xlsxTool_tFileOutputExcel_1.setXY(false, 0, 0, false);

				java.util.concurrent.ConcurrentHashMap<java.lang.Object, java.lang.Object> chm_tFileOutputExcel_1 = (java.util.concurrent.ConcurrentHashMap<java.lang.Object, java.lang.Object>) globalMap
						.get("concurrentHashMap");
				java.lang.Object lockObj_tFileOutputExcel_1 = chm_tFileOutputExcel_1
						.computeIfAbsent("EXCEL_OUTPUT_LOCK_OBJ_tFileOutputExcel_1", k -> new Object());
				synchronized (lockObj_tFileOutputExcel_1) {

					xlsxTool_tFileOutputExcel_1.prepareXlsxFile(fileName_tFileOutputExcel_1);

				}

				xlsxTool_tFileOutputExcel_1.setFont("");

				if (xlsxTool_tFileOutputExcel_1.getStartRow() == 0) {

					xlsxTool_tFileOutputExcel_1.addRow();

					xlsxTool_tFileOutputExcel_1.addCellValue("Nom_serie");

					xlsxTool_tFileOutputExcel_1.addCellValue("Zone_geographique");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42001");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12002");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22002");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32002");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42002");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12003");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22003");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32003");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42003");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12004");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22004");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32004");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42004");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12005");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22005");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32005");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42005");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12006");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22006");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32006");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42006");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12007");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22007");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32007");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42007");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12008");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22008");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32008");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42008");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12009");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22009");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32009");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42009");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12010");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22010");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32010");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42010");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12011");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22011");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32011");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42011");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12012");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22012");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32012");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42012");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12013");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22013");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32013");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42013");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12014");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22014");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32014");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42014");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12015");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22015");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32015");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42015");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12016");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22016");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32016");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42016");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12017");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22017");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32017");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42017");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12018");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22018");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32018");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42018");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12019");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22019");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32019");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42019");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12020");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22020");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32020");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42020");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12021");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22021");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32021");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42021");

					xlsxTool_tFileOutputExcel_1.addCellValue("T12022");

					xlsxTool_tFileOutputExcel_1.addCellValue("T22022");

					xlsxTool_tFileOutputExcel_1.addCellValue("T32022");

					xlsxTool_tFileOutputExcel_1.addCellValue("T42022");

					nb_line_tFileOutputExcel_1++;
					headerIsInserted_tFileOutputExcel_1 = true;

				}

				/**
				 * [tFileOutputExcel_1 begin ] stop
				 */

				/**
				 * [tMap_1 begin ] start
				 */

				ok_Hash.put("tMap_1", false);
				start_Hash.put("tMap_1", System.currentTimeMillis());

				currentComponent = "tMap_1";

				if (execStat) {
					runStat.updateStatOnConnection(resourceMap, iterateId, 0, 0, "row1");
				}

				int tos_count_tMap_1 = 0;

// ###############################
// # Lookup's keys initialization
// ###############################        

// ###############################
// # Vars initialization
				class Var__tMap_1__Struct {
				}
				Var__tMap_1__Struct Var__tMap_1 = new Var__tMap_1__Struct();
// ###############################

// ###############################
// # Outputs initialization
				Donnees_mergeesStruct Donnees_mergees_tmp = new Donnees_mergeesStruct();
// ###############################

				/**
				 * [tMap_1 begin ] stop
				 */

				/**
				 * [tFileInputExcel_1 begin ] start
				 */

				ok_Hash.put("tFileInputExcel_1", false);
				start_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				currentComponent = "tFileInputExcel_1";

				int tos_count_tFileInputExcel_1 = 0;

				final String decryptedPassword_tFileInputExcel_1 = routines.system.PasswordEncryptUtil
						.decryptPassword("enc:routine.encryption.key.v1:seIiGm0PTrAji2NRP71GaWBkfxN9vDbLEAnGew==");
				String password_tFileInputExcel_1 = decryptedPassword_tFileInputExcel_1;
				if (password_tFileInputExcel_1.isEmpty()) {
					password_tFileInputExcel_1 = null;
				}
				class RegexUtil_tFileInputExcel_1 {

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, String oneSheetName,
							boolean useRegex) {

						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();

						if (useRegex) {// this part process the regex issue

							java.util.regex.Pattern pattern = java.util.regex.Pattern.compile(oneSheetName);
							for (org.apache.poi.ss.usermodel.Sheet sheet : workbook) {
								String sheetName = sheet.getSheetName();
								java.util.regex.Matcher matcher = pattern.matcher(sheetName);
								if (matcher.matches()) {
									if (sheet != null) {
										list.add((org.apache.poi.xssf.usermodel.XSSFSheet) sheet);
									}
								}
							}

						} else {
							org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
									.getSheet(oneSheetName);
							if (sheet != null) {
								list.add(sheet);
							}

						}

						return list;
					}

					public java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> getSheets(
							org.apache.poi.xssf.usermodel.XSSFWorkbook workbook, int index, boolean useRegex) {
						java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> list = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
						org.apache.poi.xssf.usermodel.XSSFSheet sheet = (org.apache.poi.xssf.usermodel.XSSFSheet) workbook
								.getSheetAt(index);
						if (sheet != null) {
							list.add(sheet);
						}
						return list;
					}

				}
				RegexUtil_tFileInputExcel_1 regexUtil_tFileInputExcel_1 = new RegexUtil_tFileInputExcel_1();

				Object source_tFileInputExcel_1 = "D:/Alexandre/Dev/i1-mspr-data/data/donnees_insee_Talend.xlsx";
				org.apache.poi.xssf.usermodel.XSSFWorkbook workbook_tFileInputExcel_1 = null;

				if (source_tFileInputExcel_1 instanceof String) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create(new java.io.File((String) source_tFileInputExcel_1), password_tFileInputExcel_1,
									true);
				} else if (source_tFileInputExcel_1 instanceof java.io.InputStream) {
					workbook_tFileInputExcel_1 = (org.apache.poi.xssf.usermodel.XSSFWorkbook) org.apache.poi.ss.usermodel.WorkbookFactory
							.create((java.io.InputStream) source_tFileInputExcel_1, password_tFileInputExcel_1);
				} else {
					workbook_tFileInputExcel_1 = null;
					throw new java.lang.Exception("The data source should be specified as Inputstream or File Path!");
				}
				try {

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					sheetList_tFileInputExcel_1.addAll(
							regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1, "Emploi LA", false));
					sheetList_tFileInputExcel_1.addAll(regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1,
							"Emploi Indus LA", false));
					sheetList_tFileInputExcel_1.addAll(regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1,
							"Tertiaire marchand LA", false));
					sheetList_tFileInputExcel_1.addAll(regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1,
							"Tertiaire non march LA", false));
					sheetList_tFileInputExcel_1.addAll(
							regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1, "Chom LA", false));
					sheetList_tFileInputExcel_1.addAll(
							regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1, "Emploi AM", false));
					sheetList_tFileInputExcel_1.addAll(regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1,
							"Emploi Indus AM", false));
					sheetList_tFileInputExcel_1.addAll(regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1,
							"Tertiaire march AM", false));
					sheetList_tFileInputExcel_1.addAll(regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1,
							"Tertiaire non march AM OK", false));
					sheetList_tFileInputExcel_1.addAll(
							regexUtil_tFileInputExcel_1.getSheets(workbook_tFileInputExcel_1, "Chom AM", false));
					if (sheetList_tFileInputExcel_1.size() <= 0) {
						throw new RuntimeException("Special sheets not exist!");
					}

					java.util.List<org.apache.poi.xssf.usermodel.XSSFSheet> sheetList_FilterNull_tFileInputExcel_1 = new java.util.ArrayList<org.apache.poi.xssf.usermodel.XSSFSheet>();
					for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_FilterNull_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
						if (sheet_FilterNull_tFileInputExcel_1 != null
								&& sheetList_FilterNull_tFileInputExcel_1.iterator() != null
								&& sheet_FilterNull_tFileInputExcel_1.iterator().hasNext()) {
							sheetList_FilterNull_tFileInputExcel_1.add(sheet_FilterNull_tFileInputExcel_1);
						}
					}
					sheetList_tFileInputExcel_1 = sheetList_FilterNull_tFileInputExcel_1;
					if (sheetList_tFileInputExcel_1.size() > 0) {
						int nb_line_tFileInputExcel_1 = 0;

						int begin_line_tFileInputExcel_1 = 7;

						int footer_input_tFileInputExcel_1 = 0;

						int end_line_tFileInputExcel_1 = 0;
						for (org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 : sheetList_tFileInputExcel_1) {
							end_line_tFileInputExcel_1 += (sheet_tFileInputExcel_1.getLastRowNum() + 1);
						}
						end_line_tFileInputExcel_1 -= footer_input_tFileInputExcel_1;
						int limit_tFileInputExcel_1 = 9;
						int start_column_tFileInputExcel_1 = 1 - 1;
						int end_column_tFileInputExcel_1 = -1;

						org.apache.poi.xssf.usermodel.XSSFRow row_tFileInputExcel_1 = null;
						org.apache.poi.xssf.usermodel.XSSFSheet sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
								.get(0);
						int rowCount_tFileInputExcel_1 = 0;
						int sheetIndex_tFileInputExcel_1 = 0;
						int currentRows_tFileInputExcel_1 = (sheetList_tFileInputExcel_1.get(0).getLastRowNum() + 1);

						// for the number format
						java.text.DecimalFormat df_tFileInputExcel_1 = new java.text.DecimalFormat(
								"#.####################################");
						char decimalChar_tFileInputExcel_1 = df_tFileInputExcel_1.getDecimalFormatSymbols()
								.getDecimalSeparator();

						for (int i_tFileInputExcel_1 = begin_line_tFileInputExcel_1; i_tFileInputExcel_1 < end_line_tFileInputExcel_1; i_tFileInputExcel_1++) {

							int emptyColumnCount_tFileInputExcel_1 = 0;

							if (limit_tFileInputExcel_1 != -1 && nb_line_tFileInputExcel_1 >= limit_tFileInputExcel_1) {
								break;
							}

							while (i_tFileInputExcel_1 >= rowCount_tFileInputExcel_1 + currentRows_tFileInputExcel_1) {
								rowCount_tFileInputExcel_1 += currentRows_tFileInputExcel_1;
								sheet_tFileInputExcel_1 = sheetList_tFileInputExcel_1
										.get(++sheetIndex_tFileInputExcel_1);
								currentRows_tFileInputExcel_1 = (sheet_tFileInputExcel_1.getLastRowNum() + 1);
							}
							globalMap.put("tFileInputExcel_1_CURRENT_SHEET", sheet_tFileInputExcel_1.getSheetName());
							if (rowCount_tFileInputExcel_1 <= i_tFileInputExcel_1
									&& i_tFileInputExcel_1
											- rowCount_tFileInputExcel_1 >= (begin_line_tFileInputExcel_1)
									&& currentRows_tFileInputExcel_1
											- footer_input_tFileInputExcel_1 > i_tFileInputExcel_1
													- rowCount_tFileInputExcel_1) {
								row_tFileInputExcel_1 = sheet_tFileInputExcel_1
										.getRow(i_tFileInputExcel_1 - rowCount_tFileInputExcel_1);
							} else {
								continue;
							}
							row1 = null;
							int tempRowLength_tFileInputExcel_1 = 86;

							int columnIndex_tFileInputExcel_1 = 0;

							String[] temp_row_tFileInputExcel_1 = new String[tempRowLength_tFileInputExcel_1];
							int excel_end_column_tFileInputExcel_1;
							if (row_tFileInputExcel_1 == null) {
								excel_end_column_tFileInputExcel_1 = 0;
							} else {
								excel_end_column_tFileInputExcel_1 = row_tFileInputExcel_1.getLastCellNum();
							}
							int actual_end_column_tFileInputExcel_1;
							if (end_column_tFileInputExcel_1 == -1) {
								actual_end_column_tFileInputExcel_1 = excel_end_column_tFileInputExcel_1;
							} else {
								actual_end_column_tFileInputExcel_1 = end_column_tFileInputExcel_1 > excel_end_column_tFileInputExcel_1
										? excel_end_column_tFileInputExcel_1
										: end_column_tFileInputExcel_1;
							}
							org.apache.poi.ss.formula.eval.NumberEval ne_tFileInputExcel_1 = null;
							for (int i = 0; i < tempRowLength_tFileInputExcel_1; i++) {
								if (i + start_column_tFileInputExcel_1 < actual_end_column_tFileInputExcel_1) {
									org.apache.poi.ss.usermodel.Cell cell_tFileInputExcel_1 = row_tFileInputExcel_1
											.getCell(i + start_column_tFileInputExcel_1);
									if (cell_tFileInputExcel_1 != null) {
										switch (cell_tFileInputExcel_1.getCellType()) {
										case STRING:
											temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
													.getRichStringCellValue().getString();
											break;
										case NUMERIC:
											if (org.apache.poi.ss.usermodel.DateUtil
													.isCellDateFormatted(cell_tFileInputExcel_1)) {
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getDateCellValue().toString();
											} else {
												temp_row_tFileInputExcel_1[i] = df_tFileInputExcel_1
														.format(cell_tFileInputExcel_1.getNumericCellValue());
											}
											break;
										case BOOLEAN:
											temp_row_tFileInputExcel_1[i] = String
													.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
											break;
										case FORMULA:
											switch (cell_tFileInputExcel_1.getCachedFormulaResultType()) {
											case STRING:
												temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
														.getRichStringCellValue().getString();
												break;
											case NUMERIC:
												if (org.apache.poi.ss.usermodel.DateUtil
														.isCellDateFormatted(cell_tFileInputExcel_1)) {
													temp_row_tFileInputExcel_1[i] = cell_tFileInputExcel_1
															.getDateCellValue().toString();
												} else {
													ne_tFileInputExcel_1 = new org.apache.poi.ss.formula.eval.NumberEval(
															cell_tFileInputExcel_1.getNumericCellValue());
													temp_row_tFileInputExcel_1[i] = ne_tFileInputExcel_1
															.getStringValue();
												}
												break;
											case BOOLEAN:
												temp_row_tFileInputExcel_1[i] = String
														.valueOf(cell_tFileInputExcel_1.getBooleanCellValue());
												break;
											default:
												temp_row_tFileInputExcel_1[i] = "";
											}
											break;
										default:
											temp_row_tFileInputExcel_1[i] = "";
										}
									} else {
										temp_row_tFileInputExcel_1[i] = "";
									}

								} else {
									temp_row_tFileInputExcel_1[i] = "";
								}
							}
							boolean whetherReject_tFileInputExcel_1 = false;
							row1 = new row1Struct();
							int curColNum_tFileInputExcel_1 = -1;
							String curColName_tFileInputExcel_1 = "";
							try {
								columnIndex_tFileInputExcel_1 = 0;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "Zone_geographique";

									row1.Zone_geographique = temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1];
								} else {
									row1.Zone_geographique = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 1;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42001";

									row1.T42001 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42001 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 2;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12002";

									row1.T12002 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12002 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 3;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22002";

									row1.T22002 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22002 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 4;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32002";

									row1.T32002 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32002 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 5;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42002";

									row1.T42002 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42002 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 6;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12003";

									row1.T12003 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12003 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 7;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22003";

									row1.T22003 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22003 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 8;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32003";

									row1.T32003 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32003 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 9;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42003";

									row1.T42003 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42003 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 10;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12004";

									row1.T12004 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12004 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 11;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22004";

									row1.T22004 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22004 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 12;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32004";

									row1.T32004 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32004 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 13;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42004";

									row1.T42004 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42004 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 14;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12005";

									row1.T12005 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12005 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 15;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22005";

									row1.T22005 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22005 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 16;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32005";

									row1.T32005 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32005 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 17;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42005";

									row1.T42005 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42005 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 18;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12006";

									row1.T12006 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12006 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 19;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22006";

									row1.T22006 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22006 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 20;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32006";

									row1.T32006 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32006 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 21;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42006";

									row1.T42006 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42006 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 22;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12007";

									row1.T12007 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12007 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 23;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22007";

									row1.T22007 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22007 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 24;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32007";

									row1.T32007 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32007 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 25;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42007";

									row1.T42007 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42007 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 26;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12008";

									row1.T12008 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12008 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 27;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22008";

									row1.T22008 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22008 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 28;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32008";

									row1.T32008 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32008 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 29;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42008";

									row1.T42008 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42008 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 30;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12009";

									row1.T12009 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12009 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 31;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22009";

									row1.T22009 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22009 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 32;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32009";

									row1.T32009 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32009 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 33;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42009";

									row1.T42009 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42009 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 34;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12010";

									row1.T12010 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12010 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 35;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22010";

									row1.T22010 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22010 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 36;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32010";

									row1.T32010 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32010 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 37;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42010";

									row1.T42010 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42010 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 38;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12011";

									row1.T12011 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12011 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 39;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22011";

									row1.T22011 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22011 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 40;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32011";

									row1.T32011 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32011 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 41;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42011";

									row1.T42011 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42011 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 42;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12012";

									row1.T12012 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12012 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 43;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22012";

									row1.T22012 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22012 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 44;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32012";

									row1.T32012 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32012 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 45;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42012";

									row1.T42012 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42012 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 46;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12013";

									row1.T12013 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12013 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 47;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22013";

									row1.T22013 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22013 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 48;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32013";

									row1.T32013 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32013 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 49;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42013";

									row1.T42013 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42013 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 50;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12014";

									row1.T12014 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12014 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 51;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22014";

									row1.T22014 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22014 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 52;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32014";

									row1.T32014 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32014 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 53;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42014";

									row1.T42014 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42014 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 54;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12015";

									row1.T12015 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12015 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 55;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22015";

									row1.T22015 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22015 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 56;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32015";

									row1.T32015 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32015 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 57;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42015";

									row1.T42015 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42015 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 58;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12016";

									row1.T12016 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12016 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 59;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22016";

									row1.T22016 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22016 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 60;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32016";

									row1.T32016 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32016 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 61;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42016";

									row1.T42016 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42016 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 62;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12017";

									row1.T12017 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12017 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 63;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22017";

									row1.T22017 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22017 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 64;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32017";

									row1.T32017 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32017 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 65;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42017";

									row1.T42017 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42017 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 66;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12018";

									row1.T12018 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12018 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 67;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22018";

									row1.T22018 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22018 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 68;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32018";

									row1.T32018 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32018 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 69;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42018";

									row1.T42018 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42018 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 70;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12019";

									row1.T12019 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12019 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 71;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22019";

									row1.T22019 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22019 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 72;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32019";

									row1.T32019 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32019 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 73;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42019";

									row1.T42019 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42019 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 74;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12020";

									row1.T12020 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12020 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 75;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22020";

									row1.T22020 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22020 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 76;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32020";

									row1.T32020 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32020 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 77;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42020";

									row1.T42020 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42020 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 78;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12021";

									row1.T12021 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12021 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 79;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22021";

									row1.T22021 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22021 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 80;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32021";

									row1.T32021 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32021 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 81;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42021";

									row1.T42021 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42021 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 82;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T12022";

									row1.T12022 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T12022 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 83;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T22022";

									row1.T22022 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T22022 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 84;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T32022";

									row1.T32022 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T32022 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}
								columnIndex_tFileInputExcel_1 = 85;

								if (temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1].length() > 0) {
									curColNum_tFileInputExcel_1 = columnIndex_tFileInputExcel_1
											+ start_column_tFileInputExcel_1 + 1;
									curColName_tFileInputExcel_1 = "T42022";

									row1.T42022 = ParserUtils.parseTo_Double(ParserUtils.parseTo_Number(
											temp_row_tFileInputExcel_1[columnIndex_tFileInputExcel_1], null,
											'.' == decimalChar_tFileInputExcel_1 ? null
													: decimalChar_tFileInputExcel_1));
								} else {
									row1.T42022 = null;
									emptyColumnCount_tFileInputExcel_1++;
								}

								nb_line_tFileInputExcel_1++;

							} catch (java.lang.Exception e) {
								globalMap.put("tFileInputExcel_1_ERROR_MESSAGE", e.getMessage());
								whetherReject_tFileInputExcel_1 = true;
								throw (e);
							}

							/**
							 * [tFileInputExcel_1 begin ] stop
							 */

							/**
							 * [tFileInputExcel_1 main ] start
							 */

							currentComponent = "tFileInputExcel_1";

							tos_count_tFileInputExcel_1++;

							/**
							 * [tFileInputExcel_1 main ] stop
							 */

							/**
							 * [tFileInputExcel_1 process_data_begin ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_begin ] stop
							 */
// Start of branch "row1"
							if (row1 != null) {

								/**
								 * [tMap_1 main ] start
								 */

								currentComponent = "tMap_1";

								if (execStat) {
									runStat.updateStatOnConnection(iterateId, 1, 1

											, "row1"

									);
								}

								boolean hasCasePrimitiveKeyWithNull_tMap_1 = false;

								// ###############################
								// # Input tables (lookups)
								boolean rejectedInnerJoin_tMap_1 = false;
								boolean mainRowRejected_tMap_1 = false;

								// ###############################
								{ // start of Var scope

									// ###############################
									// # Vars tables

									Var__tMap_1__Struct Var = Var__tMap_1;// ###############################
									// ###############################
									// # Output tables

									Donnees_mergees = null;

// # Output table : 'Donnees_mergees'
									Donnees_mergees_tmp.Nom_serie = ((String) globalMap
											.get("tFileInputExcel_1_CURRENT_SHEET"));
									Donnees_mergees_tmp.Zone_geographique = row1.Zone_geographique;
									Donnees_mergees_tmp.T42001 = row1.T42001;
									Donnees_mergees_tmp.T12002 = row1.T12002;
									Donnees_mergees_tmp.T22002 = row1.T22002;
									Donnees_mergees_tmp.T32002 = row1.T32002;
									Donnees_mergees_tmp.T42002 = row1.T42002;
									Donnees_mergees_tmp.T12003 = row1.T12003;
									Donnees_mergees_tmp.T22003 = row1.T22003;
									Donnees_mergees_tmp.T32003 = row1.T32003;
									Donnees_mergees_tmp.T42003 = row1.T42003;
									Donnees_mergees_tmp.T12004 = row1.T12004;
									Donnees_mergees_tmp.T22004 = row1.T22004;
									Donnees_mergees_tmp.T32004 = row1.T32004;
									Donnees_mergees_tmp.T42004 = row1.T42004;
									Donnees_mergees_tmp.T12005 = row1.T12005;
									Donnees_mergees_tmp.T22005 = row1.T22005;
									Donnees_mergees_tmp.T32005 = row1.T32005;
									Donnees_mergees_tmp.T42005 = row1.T42005;
									Donnees_mergees_tmp.T12006 = row1.T12006;
									Donnees_mergees_tmp.T22006 = row1.T22006;
									Donnees_mergees_tmp.T32006 = row1.T32006;
									Donnees_mergees_tmp.T42006 = row1.T42006;
									Donnees_mergees_tmp.T12007 = row1.T12007;
									Donnees_mergees_tmp.T22007 = row1.T22007;
									Donnees_mergees_tmp.T32007 = row1.T32007;
									Donnees_mergees_tmp.T42007 = row1.T42007;
									Donnees_mergees_tmp.T12008 = row1.T12008;
									Donnees_mergees_tmp.T22008 = row1.T22008;
									Donnees_mergees_tmp.T32008 = row1.T32008;
									Donnees_mergees_tmp.T42008 = row1.T42008;
									Donnees_mergees_tmp.T12009 = row1.T12009;
									Donnees_mergees_tmp.T22009 = row1.T22009;
									Donnees_mergees_tmp.T32009 = row1.T32009;
									Donnees_mergees_tmp.T42009 = row1.T42009;
									Donnees_mergees_tmp.T12010 = row1.T12010;
									Donnees_mergees_tmp.T22010 = row1.T22010;
									Donnees_mergees_tmp.T32010 = row1.T32010;
									Donnees_mergees_tmp.T42010 = row1.T42010;
									Donnees_mergees_tmp.T12011 = row1.T12011;
									Donnees_mergees_tmp.T22011 = row1.T22011;
									Donnees_mergees_tmp.T32011 = row1.T32011;
									Donnees_mergees_tmp.T42011 = row1.T42011;
									Donnees_mergees_tmp.T12012 = row1.T12012;
									Donnees_mergees_tmp.T22012 = row1.T22012;
									Donnees_mergees_tmp.T32012 = row1.T32012;
									Donnees_mergees_tmp.T42012 = row1.T42012;
									Donnees_mergees_tmp.T12013 = row1.T12013;
									Donnees_mergees_tmp.T22013 = row1.T22013;
									Donnees_mergees_tmp.T32013 = row1.T32013;
									Donnees_mergees_tmp.T42013 = row1.T42013;
									Donnees_mergees_tmp.T12014 = row1.T12014;
									Donnees_mergees_tmp.T22014 = row1.T22014;
									Donnees_mergees_tmp.T32014 = row1.T32014;
									Donnees_mergees_tmp.T42014 = row1.T42014;
									Donnees_mergees_tmp.T12015 = row1.T12015;
									Donnees_mergees_tmp.T22015 = row1.T22015;
									Donnees_mergees_tmp.T32015 = row1.T32015;
									Donnees_mergees_tmp.T42015 = row1.T42015;
									Donnees_mergees_tmp.T12016 = row1.T12016;
									Donnees_mergees_tmp.T22016 = row1.T22016;
									Donnees_mergees_tmp.T32016 = row1.T32016;
									Donnees_mergees_tmp.T42016 = row1.T42016;
									Donnees_mergees_tmp.T12017 = row1.T12017;
									Donnees_mergees_tmp.T22017 = row1.T22017;
									Donnees_mergees_tmp.T32017 = row1.T32017;
									Donnees_mergees_tmp.T42017 = row1.T42017;
									Donnees_mergees_tmp.T12018 = row1.T12018;
									Donnees_mergees_tmp.T22018 = row1.T22018;
									Donnees_mergees_tmp.T32018 = row1.T32018;
									Donnees_mergees_tmp.T42018 = row1.T42018;
									Donnees_mergees_tmp.T12019 = row1.T12019;
									Donnees_mergees_tmp.T22019 = row1.T22019;
									Donnees_mergees_tmp.T32019 = row1.T32019;
									Donnees_mergees_tmp.T42019 = row1.T42019;
									Donnees_mergees_tmp.T12020 = row1.T12020;
									Donnees_mergees_tmp.T22020 = row1.T22020;
									Donnees_mergees_tmp.T32020 = row1.T32020;
									Donnees_mergees_tmp.T42020 = row1.T42020;
									Donnees_mergees_tmp.T12021 = row1.T12021;
									Donnees_mergees_tmp.T22021 = row1.T22021;
									Donnees_mergees_tmp.T32021 = row1.T32021;
									Donnees_mergees_tmp.T42021 = row1.T42021;
									Donnees_mergees_tmp.T12022 = row1.T12022;
									Donnees_mergees_tmp.T22022 = row1.T22022;
									Donnees_mergees_tmp.T32022 = row1.T32022;
									Donnees_mergees_tmp.T42022 = row1.T42022;
									Donnees_mergees = Donnees_mergees_tmp;
// ###############################

								} // end of Var scope

								rejectedInnerJoin_tMap_1 = false;

								tos_count_tMap_1++;

								/**
								 * [tMap_1 main ] stop
								 */

								/**
								 * [tMap_1 process_data_begin ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_begin ] stop
								 */
// Start of branch "Donnees_mergees"
								if (Donnees_mergees != null) {

									/**
									 * [tFileOutputExcel_1 main ] start
									 */

									currentComponent = "tFileOutputExcel_1";

									if (execStat) {
										runStat.updateStatOnConnection(iterateId, 1, 1

												, "Donnees_mergees"

										);
									}

									xlsxTool_tFileOutputExcel_1.addRow();

									if (Donnees_mergees.Nom_serie != null) {

										xlsxTool_tFileOutputExcel_1
												.addCellValue(String.valueOf(Donnees_mergees.Nom_serie));
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.Zone_geographique != null) {

										xlsxTool_tFileOutputExcel_1
												.addCellValue(String.valueOf(Donnees_mergees.Zone_geographique));
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42001 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42001);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12002 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12002);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22002 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22002);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32002 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32002);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42002 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42002);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12003 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12003);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22003 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22003);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32003 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32003);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42003 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42003);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12004 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12004);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22004 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22004);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32004 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32004);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42004 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42004);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12005 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12005);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22005 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22005);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32005 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32005);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42005 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42005);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12006 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12006);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22006 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22006);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32006 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32006);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42006 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42006);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12007 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12007);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22007 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22007);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32007 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32007);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42007 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42007);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12008 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12008);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22008 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22008);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32008 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32008);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42008 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42008);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12009 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12009);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22009 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22009);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32009 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32009);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42009 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42009);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12010 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12010);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22010 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22010);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32010 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32010);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42010 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42010);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12011 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12011);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22011 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22011);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32011 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32011);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42011 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42011);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12012 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12012);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22012 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22012);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32012 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32012);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42012 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42012);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12013 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12013);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22013 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22013);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32013 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32013);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42013 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42013);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12014 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12014);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22014 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22014);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32014 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32014);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42014 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42014);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12015 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12015);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22015 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22015);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32015 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32015);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42015 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42015);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12016 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12016);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22016 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22016);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32016 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32016);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42016 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42016);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12017 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12017);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22017 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22017);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32017 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32017);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42017 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42017);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12018 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12018);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22018 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22018);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32018 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32018);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42018 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42018);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12019 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12019);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22019 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22019);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32019 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32019);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42019 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42019);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12020 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12020);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22020 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22020);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32020 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32020);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42020 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42020);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12021 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12021);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22021 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22021);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32021 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32021);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42021 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42021);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T12022 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T12022);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T22022 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T22022);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T32022 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T32022);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									if (Donnees_mergees.T42022 != null) {

										xlsxTool_tFileOutputExcel_1.addCellValue(Donnees_mergees.T42022);
									} else {
										xlsxTool_tFileOutputExcel_1.addCellNullValue();
									}

									nb_line_tFileOutputExcel_1++;

									tos_count_tFileOutputExcel_1++;

									/**
									 * [tFileOutputExcel_1 main ] stop
									 */

									/**
									 * [tFileOutputExcel_1 process_data_begin ] start
									 */

									currentComponent = "tFileOutputExcel_1";

									/**
									 * [tFileOutputExcel_1 process_data_begin ] stop
									 */

									/**
									 * [tFileOutputExcel_1 process_data_end ] start
									 */

									currentComponent = "tFileOutputExcel_1";

									/**
									 * [tFileOutputExcel_1 process_data_end ] stop
									 */

								} // End of branch "Donnees_mergees"

								/**
								 * [tMap_1 process_data_end ] start
								 */

								currentComponent = "tMap_1";

								/**
								 * [tMap_1 process_data_end ] stop
								 */

							} // End of branch "row1"

							/**
							 * [tFileInputExcel_1 process_data_end ] start
							 */

							currentComponent = "tFileInputExcel_1";

							/**
							 * [tFileInputExcel_1 process_data_end ] stop
							 */

							/**
							 * [tFileInputExcel_1 end ] start
							 */

							currentComponent = "tFileInputExcel_1";

						}

						globalMap.put("tFileInputExcel_1_NB_LINE", nb_line_tFileInputExcel_1);

					}

				} finally {

					if (!(source_tFileInputExcel_1 instanceof java.io.InputStream)) {
						workbook_tFileInputExcel_1.getPackage().revert();
					}

				}

				ok_Hash.put("tFileInputExcel_1", true);
				end_Hash.put("tFileInputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileInputExcel_1 end ] stop
				 */

				/**
				 * [tMap_1 end ] start
				 */

				currentComponent = "tMap_1";

// ###############################
// # Lookup hashes releasing
// ###############################      

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "row1");
				}

				ok_Hash.put("tMap_1", true);
				end_Hash.put("tMap_1", System.currentTimeMillis());

				/**
				 * [tMap_1 end ] stop
				 */

				/**
				 * [tFileOutputExcel_1 end ] start
				 */

				currentComponent = "tFileOutputExcel_1";

				columnIndex_tFileOutputExcel_1 = 0;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 1;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 2;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 3;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 4;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 5;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 6;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 7;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 8;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 9;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 10;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 11;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 12;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 13;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 14;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 15;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 16;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 17;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 18;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 19;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 20;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 21;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 22;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 23;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 24;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 25;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 26;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 27;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 28;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 29;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 30;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 31;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 32;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 33;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 34;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 35;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 36;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 37;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 38;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 39;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 40;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 41;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 42;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 43;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 44;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 45;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 46;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 47;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 48;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 49;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 50;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 51;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 52;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 53;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 54;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 55;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 56;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 57;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 58;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 59;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 60;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 61;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 62;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 63;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 64;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 65;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 66;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 67;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 68;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 69;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 70;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 71;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 72;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 73;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 74;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 75;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 76;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 77;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 78;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 79;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 80;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 81;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 82;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 83;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 84;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 85;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				columnIndex_tFileOutputExcel_1 = 86;

				xlsxTool_tFileOutputExcel_1.setColAutoSize(columnIndex_tFileOutputExcel_1);

				xlsxTool_tFileOutputExcel_1.writeExcel(fileName_tFileOutputExcel_1, true);

				if (headerIsInserted_tFileOutputExcel_1 && nb_line_tFileOutputExcel_1 > 0) {
					nb_line_tFileOutputExcel_1 = nb_line_tFileOutputExcel_1 - 1;
				}
				globalMap.put("tFileOutputExcel_1_NB_LINE", nb_line_tFileOutputExcel_1);

				if (execStat) {
					runStat.updateStat(resourceMap, iterateId, 2, 0, "Donnees_mergees");
				}

				ok_Hash.put("tFileOutputExcel_1", true);
				end_Hash.put("tFileOutputExcel_1", System.currentTimeMillis());

				/**
				 * [tFileOutputExcel_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			TalendException te = new TalendException(e, currentComponent, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tFileInputExcel_1 finally ] start
				 */

				currentComponent = "tFileInputExcel_1";

				/**
				 * [tFileInputExcel_1 finally ] stop
				 */

				/**
				 * [tMap_1 finally ] start
				 */

				currentComponent = "tMap_1";

				/**
				 * [tMap_1 finally ] stop
				 */

				/**
				 * [tFileOutputExcel_1 finally ] start
				 */

				currentComponent = "tFileOutputExcel_1";

				/**
				 * [tFileOutputExcel_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	public static void main(String[] args) {
		final Concatenation_fichier_emploi Concatenation_fichier_emploiClass = new Concatenation_fichier_emploi();

		int exitCode = Concatenation_fichier_emploiClass.runJobInTOS(args);

		System.exit(exitCode);
	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}
		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		if (rootPid == null) {
			rootPid = pid;
		}
		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (inOSGi) {
			java.util.Dictionary<String, Object> jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

			if (jobProperties != null && jobProperties.get("context") != null) {
				contextStr = (String) jobProperties.get("context");
			}
		}

		try {
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = Concatenation_fichier_emploi.class.getClassLoader().getResourceAsStream(
					"mspr_big_data/concatenation_fichier_emploi_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = Concatenation_fichier_emploi.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						context = new ContextProperties(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}

			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, parametersToEncrypt));

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tFileInputExcel_1Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tFileInputExcel_1) {
			globalMap.put("tFileInputExcel_1_SUBPROCESS_STATE", -1);

			e_tFileInputExcel_1.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory)
					+ " bytes memory increase when running : Concatenation_fichier_emploi");
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 331760 characters generated by Talend Open Studio for Data Integration on the
 * 22 juillet 2023 à 12:52:11 CEST
 ************************************************************************************************/