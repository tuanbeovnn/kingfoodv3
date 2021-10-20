package com.kingfood.backend.constants;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public final class Constants {
    public static class Status{
        public static final String ACTIVE = "ACTIVE";
        public static final String DRAFF = "DRAFF";
        public static final String DELETED = "DELETED";
    }

    public static final String ROW_ID = "rowId";
    public static final String ROW_IDS = "rowIds";
    public static final String ERROR_ITEM = "item";
    public static final String ERROR_CODE = "errorCode";
    public static final String ERROR_PARAMS = "errorParams";
    public static final int RESPONSE_SUCCESS = 0;
    public static final int RESPONSE_FAILED = 1;

    public static final String SYSTEM_ACCOUNT = "system";
    public static final String ANONYMOUS_USER = "anonymoususer";
    public static final String DEFAULT_TENANT_ID = "default";
    public static final String HEADER_TENANT_ID = "X-TenantID";
    public static final String HEADER_ACCESS_TOKEN = "X-AccessToken";
    public static final String HEADER_TIMEZONE = "timezone";
    public static final String UTC_TIMEZONE = "UTC";
    public static final String EMPLOYEE_ID = "employeeId";
    public static final String LANGUAGE_CODE = "languageCode";
    public static final String EMPLOYEE_NAME = "employeeName";
    public static final String LICENSES = "licenses";
    public static final String EMAIL = "email";
    public static final String DYNAMIC_DATE_TIME_VALIUE_KEY = "date_time";
    public static final String DYNAMIC_DATE_VALIUE_KEY = "date";
    public static final String DYNAMIC_TIME_VALIUE_KEY = "time";
    public static final String DYNAMIC_VALIUE_KEY = "value";
    public static final String DYNAMIC_LABEL_KEY = "label";

    public static final String PERIOD = ".";
    public static final String COMMA = ",";
    public static final String EMPTY = "";
    public static final String NULL_STRING = "null";
    public static final String FORMAT_COLUMN_SEARCH = "%.%";

    public static final String FIELD_NAME_DEPARTMENT_ID = "department_id";
    public static final String FIELD_NAME_EMPLOYEE_ID = "employee_id";
    public static final String FIELD_NAME_GROUP_ID = "group_id";

    public static final String TENANT_FORMAT = "^(?=.*[a-z0-9])[a-z0-9]+$";
    public static final String URL_API_GET_TENANT = "/public/api/get-tenant-by-condition";
    public static final String URL_API_GET_SERVICES_OF_USER = "get-services";

    public static final String TENANTS_BATCH = "tenantforbatch";

    public static final String TENANTS_SERVICE = "tenants";
    public static final String LIST_OF_PRODUCT_TRADING_ID = "listOfProductTradingId";
    public static final String PRODUCT_TRADING_ID = "productTradingId";
    public static final String ID_OF_NEW_LIST = "idOfNewList";
    public static final String ID_OF_OLD_LIST = "idOfOldList";
    public static final String DEFAULT_TIMEZONE = "Asia/Tokyo";

    /**
     * 接続に失敗しました。
     */
    public static final String CONNECT_FAILED_CODE = "ERR_COM_0001";

    /**
     * 無効な日付フォーマット·エラー·コード
     */
    public static final String FORMAT_DATE_CODE = "ERR_COM_0034";
    /**
     * フォーマット無効なエラーコード
     */
    public static final String FORMAT_INVALID_CODE = "ERR_COM_0034";
    public static final String FORMAT_INVALID_NUMBER_HYPHEN_CODE = "ERR_COM_0034";

    /**
     * メールを取得するリストID
     */
    public static final String PARAMETER_INVALID = "ERR_COM_0035";

    /**
     * Email invalid code
     */
    public static final String EMAIL_INVALID_CODE = "ERR_COM_0017";

    /**
     * Email invalid code
     */
    public static final String PHONE_INVALID_CODE = "ERR_COM_0020";

    /**
     * TIME invalid code
     */
    public static final String TIME_INVALID_CODE = "ERR_COM_0019";
    /**
     * DATETIME invalid code
     */
    public static final String DATETIME_INVALID_CODE = "ERR_COM_0054";
    /**
     * DATE invalid code
     */
    public static final String DATE_INVALID_CODE = "ERR_COM_0018";
    /**
     * ALPHABE invalid code
     */
    public static final String ALPHABE_INVALID_CODE = "ERR_COM_0021";

    /**
     * DECIMAL invalid code
     */
    public static final String DECIMAL_INVALID_CODE = "ERR_COM_0015";
    /**
     * 無効なエラーコード番号
     */
    public static final String NUMBER_INVALID_CODE = "ERR_COM_0015";
    /**
     * emoji
     */
    public static final String EMOJI_INVALID_CODE = "ERR_COM_0015";

    /**
     * greater than maxlength
     */
    public static final String GREATER_LENGTH_CODE = "ERR_COM_0025";
    /**
     * less length
     */
    public static final String LESS_LENGTH_CODE = "ERR_COM_0026";
    /**
     * 長エラーコード上正の部分
     */
    public static final String POSITIVE_CODE = "ERR_COM_0029";
    /**
     * 長エラーコード上小数部
     */
    public static final String DECIMAL_CODE = "ERR_COM_0030";
    /**
     * MAXよりエラーコード番号より
     */
    public static final String NUMBER_MAX_CODE = "ERR_COM_0027";
    /**
     * MINエラーコード番号少ない石炭
     */
    public static final String NUMBER_MIN_CODE = "ERR_COM_0028";
    /**
     * エラーコードが必要に
     */
    public static final String RIQUIRED_CODE = "ERR_COM_0013";
    /**
     * productCategoryLevel greater than 15
     */
    public static final String CATEGORY_LEVEL_GREATER_THAN_15 = "ERR_PRO_0002";
    /**
     * {0}の形式で入力してください。
     */
    public static final String MAIL_VALIDATE_ERROR = "ERR_COM_0034";
    public static final String USER_NOT_ADMIN = "ERR_COM_0007";

    public static final String CREATE_FAILED = "ERR_COM_0003";

    public static final String UPDATE_FAILED = "ERR_COM_0004";

    public static final String DELETE_FAILED = "ERR_COM_0005";

    public static final String MAX_LENGTH = "ERR_COM_0025";

    public static final String INTERRUPT_API = "ERR_COM_0012";

    public static final String ITEM_NOT_EXIST = "ERR_COM_0035";

    public static final String TENANT_IS_EXIST = "ERR_COM_0036";

    public static final String ERR_COM_0070 = "ERR_COM_0070";

    public static final String ERR_COM_0067 = "ERR_COM_0067";

    public static final String ERR_COM_0068 = "ERR_COM_0068";

    public static final String ERR_COM_0069 = "ERR_COM_0069";

    public static final String ERR_COM_0078 = "ERR_COM_0078";

    public static final String SEARCH_CONDITION_AUTO_NOT_EXIST = "ERR_COM_0059";

    public static final String AT_LEAST_1_IS_OWNER = "ERR_COM_0061";

    public static final String REQUIRED_PARAMETER = "ERR_TEN_0001";

    public static final String STATUS_NOT_STARTUP = "ERR_TEN_0002";

    public static final String INTEGER_PARAMETER = "ERR_TEN_0003";

    public static final String CREATED = "INF_COM_0003";

    public static final String UPDATED = "INF_COM_0004";

    public static final String ESTABLISH_SUCCESS = "INF_COM_0008";

    public static final String NO_DATA_SEARCH = "INF_COM_0007";

    public static final String TENANT_CREATING_ERROR = "ERR_TEN_0004";

    public static final String RANGE_DATE_CODE = "ERR_COM_0082";

    public static final String LOOK_UP_ERROR = "ERR_COM_0084";

    public static final String KEY_LOOK_UP_ERROR = "ERR_COM_0087";

    public static final String RELATION_ERROR = "ERR_COM_0085";

    public static final String CALCULATOR_ERROR = "ERR_COM_0086";

    /**
     * テナント null
     */
    public static final String TENANTS_NOT_NULL = "ERR_COM_0036";

    public static final String TENANTS_MESSAGE_SUCCESS = "INFO_TEN_001";

    public static final String ITEM_IS_NOT_EXISTED = "ERR_BUS_0002";
    public static final String ITEM_IS_DELETED = "ERR_BUS_0003";
    public static final String ITEM_IS_EXISTED = "ERR_BUS_0004";
    public static final String REDIS_USER_ACCESS_KEY = "user_access_time";

    /**
     * テキストエンコーディングの定数
     */
    public static final class Text {
        private Text() {
            // do nothing
        }

        public static final String WINDOWS_31J = "WINDOWS-31J";
        public static final String CRLF = "\r\n";
    }

    /**
     * Field belong enum
     */
    public enum FieldBelong {
        EMPLOYEE(8), CUSTOMER(5), BUSINESS_CARD(4), TIMELINE(3), TIMELINE_GROUP(301), LOG_ACCESS(2101), ACTIVITY(6),
        PRODUCT(14), TASK(15), MILESTONE(1501), SCHEDULE(2), TRADING_PRODUCT(16), PRODUCT_ITEM(1401), DEPARTMENT(801),
        SALE(16), ANALYSIS(10);

        private int value;

        private FieldBelong(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static FieldBelong getEnum(int value) {
            for (FieldBelong fieldBelong : values()) {
                if (fieldBelong.getValue() == value)
                    return fieldBelong;
            }
            return null;
        }

        public static List<Integer> getFieldBelongList() {
            List<Integer> fieldBelongList = new ArrayList<>();
            for (FieldBelong fieldBelong : values()) {
                fieldBelongList.add(fieldBelong.getValue());
            }
            return fieldBelongList;
        }
    }

    /**
     * Tab belong enum
     */
    public enum TabBelong {
        EMPLOYEE(8), CUSTOMER(5), BUSINESS_CARD(4), TIMELINE(3), TIMELINE_GROUP(301), LOG_ACCESS(2101), ACTIVITY(6),
        PRODUCT(14), TASK(15), MILESTONE(1501), SCHEDULE(2), TRADING_PRODUCT(16), PRODUCT_ITEM(1401);

        private int value;

        private TabBelong(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<Integer> getTabBelongList() {
            List<Integer> tabBelongList = new ArrayList<>();
            for (TabBelong tabBelong : values()) {
                tabBelongList.add(tabBelong.getValue());
            }
            return tabBelongList;
        }
    }

    /**
     * Modify Flag enum
     */
    public enum ModifyFlag {
        READ_ONLY(0), ANY(1), REQUIRED(2), DEFAULT_REQUIRED(3);

        private Integer value;

        private ModifyFlag(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * Extension belong enum
     */
    public enum ExtensionBelong {
        SHOW_IN_LIST(1), SEARCH(2);

        private int value;

        private ExtensionBelong(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }

        public static List<Integer> getExtensionBelongList() {
            List<Integer> extensionBelongList = new ArrayList<>();
            for (ExtensionBelong extensionBelong : values()) {
                extensionBelongList.add(extensionBelong.getValue());
            }
            return extensionBelongList;
        }
    }

    /**
     * Available Flag enum
     */
    public enum AvailableFlag {
        UNAVAILABLE(0), AVAILABLE(3);

        private Integer value;

        private AvailableFlag(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * Relation Format enum
     */
    public enum RelationFormat {
        SINGLE(1), MULTI(2);

        private Integer value;

        private RelationFormat(Integer value) {
            this.value = value;
        }

        public Integer getValue() {
            return value;
        }
    }

    /**
     * File extension
     */
    public static final class Elasticsearch {
        private Elasticsearch() {
            // do nothing
        }

        public static final String INDEX_FIELD = "index";
        public static final String SUFFIX_FIELD_FULLTEXT = "_str";
        public static final int DEFAULT_LIMIT = 1000000;
        public static final String TRUE_VALUE = "true";
        public static final String TO_OPTIONAL = "toOptional";
        public static final String FROM_OPTIONAL = "fromOptional";
        public static final String ELASTICSEARCH_INDEX_FORMAT = "%s_%s";

        /**
         * search operator 1: OR, 2: AND
         */
        public enum Operator {
            OR(1), AND(2);

            private int value;

            private Operator(int value) {
                this.value = value;
            }

            public int getValue() {
                return value;
            }
        }

        /**
         * define elasticsearch index
         */
        private static Map<Integer, String> indexMap = new HashMap<>();

        static {
            indexMap.put(FieldBelong.EMPLOYEE.getValue(), "employee");
            indexMap.put(FieldBelong.CUSTOMER.getValue(), "customer");
            indexMap.put(FieldBelong.BUSINESS_CARD.getValue(), "businesscard");
            indexMap.put(FieldBelong.SCHEDULE.getValue(), "calendar");
            indexMap.put(FieldBelong.PRODUCT.getValue(), "product");
            indexMap.put(FieldBelong.ACTIVITY.getValue(), "activity");
            indexMap.put(FieldBelong.TRADING_PRODUCT.getValue(), "sale");
            indexMap.put(FieldBelong.ANALYSIS.getValue(), "analysis_data_set");

        }

        /**
         * define main table of function
         */
        private static Map<Integer, String> tableMainMap = new HashMap<>();

        static {
            tableMainMap.put(FieldBelong.EMPLOYEE.getValue(), MICRO_SERVICE_EMPLOYEES);
            tableMainMap.put(FieldBelong.CUSTOMER.getValue(), MICRO_SERVICE_CUSTOMERS);
            tableMainMap.put(FieldBelong.BUSINESS_CARD.getValue(), "business_cards");
            tableMainMap.put(FieldBelong.SCHEDULE.getValue(), "calendars");
            tableMainMap.put(FieldBelong.TASK.getValue(), "tasks");
            tableMainMap.put(FieldBelong.PRODUCT.getValue(), MICRO_SERVICE_PRODUCTS);
            tableMainMap.put(FieldBelong.ACTIVITY.getValue(), MICRO_SERVICE_ACTIVITIES);
            tableMainMap.put(FieldBelong.TIMELINE.getValue(), MICRO_SERVICE_TIMELINES);
            tableMainMap.put(FieldBelong.TRADING_PRODUCT.getValue(), "products_tradings");
        }

        /**
         * define column data
         */
        private static Map<Integer, String> columnDataMap = new HashMap<>();

        static {
            columnDataMap.put(FieldBelong.EMPLOYEE.getValue(), "employee_data");
            columnDataMap.put(FieldBelong.CUSTOMER.getValue(), "customer_data");
            columnDataMap.put(FieldBelong.BUSINESS_CARD.getValue(), "business_card_data");
            columnDataMap.put(FieldBelong.SCHEDULE.getValue(), "calendar_data");
            columnDataMap.put(FieldBelong.TASK.getValue(), "task_data");
            columnDataMap.put(FieldBelong.PRODUCT.getValue(), "product_data");
            columnDataMap.put(FieldBelong.ACTIVITY.getValue(), "activity_data");
            columnDataMap.put(FieldBelong.TIMELINE.getValue(), "timeline_data");
            columnDataMap.put(FieldBelong.TRADING_PRODUCT.getValue(), "product_trading_data");
        }

        /**
         * define column primary key
         */
        private static Map<Integer, String> columnPKMap = new HashMap<>();

        static {
            columnPKMap.put(FieldBelong.EMPLOYEE.getValue(), FIELD_NAME_EMPLOYEE_ID);
            columnPKMap.put(FieldBelong.CUSTOMER.getValue(), "customer_id");
            columnPKMap.put(FieldBelong.BUSINESS_CARD.getValue(), "business_card_id");
            columnPKMap.put(FieldBelong.SCHEDULE.getValue(), "calendar_id");
            columnPKMap.put(FieldBelong.TASK.getValue(), "task_id");
            columnPKMap.put(FieldBelong.PRODUCT.getValue(), "product_id");
            columnPKMap.put(FieldBelong.ACTIVITY.getValue(), "activity_id");
            columnPKMap.put(FieldBelong.TIMELINE.getValue(), "timeline_id");
            columnPKMap.put(FieldBelong.TRADING_PRODUCT.getValue(), "product_trading_id");
        }

        /**
         * get elasticsearch index by field belong
         *
         * @param fieldBelong
         * @return
         */
        public static String getIndexName(int fieldBelong) {
            return indexMap.get(fieldBelong);
        }

        /**
         * get main table of function
         *
         * @param fieldBelong
         * @return
         */
        public static String getTableMain(int fieldBelong) {
            return tableMainMap.get(fieldBelong);
        }

        /**
         * get column data by field belong
         *
         * @param fieldBelong
         * @return
         */
        public static String getColumnData(int fieldBelong) {
            return columnDataMap.get(fieldBelong);
        }

        /**
         * get column primary by field belong
         *
         * @param fieldBelong
         * @return
         */
        public static String getColumnPrimary(int fieldBelong) {
            return columnPKMap.get(fieldBelong);
        }


    }

    /**
     * Action change code
     */
    public enum ChangeAction {
        DELETE(0), INSERT(1), UPDATE(2);

        private int value;

        private ChangeAction(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Search type
     */
    public enum SearchTypeEnum {
        LIKE(1), FIRST_MATCH(2), OTHER(3);

        private int value;

        private SearchTypeEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Search option
     */
    public enum SearchOptionEnum {
        OR(1), AND(2), ALL_WORD(3), NOT_OR(4), NOT_AND(5);

        private int value;

        private SearchOptionEnum(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * File extension
     */
    public static final class FileExtension {
        private FileExtension() {
            // do nothing
        }

        public static final String CSV = "csv";
    }

    /**
     * The roles of user
     */
    public static final class Roles {
        private Roles() {
            // do nothing
        }

        public static final String ROLE_ADMIN = "ROLE_ADMIN";
        public static final String ROLE_USER = "ROLE_USER";
        public static final String ROLE_ANONYMOUS = "ROLE_ANONYMOUS";
    }

    /**
     * The roles of user
     */
    public static final class Authenticate {
        private Authenticate() {
            // do nothing
        }

        public static final String TENANT_ID = "custom:tenant_id";
        public static final String EMPLOYEE_ID = "custom:employee_id";
        public static final String LANGUAGE_CODE = "custom:language_code";
        public static final String EMPLOYEE_NAME = "custom:employee_name";
        public static final String FORMAT_DATE = "custom:format_date";
        public static final String TIMEZONE = "custom:timezone_name";
        public static final String IS_ACCESS_CONTRACT = "custom:is_access_contract";
        public static final String IS_MODIFY_EMPLOYEE = "custom:is_modify_employee";
        public static final String LICENSES = "licenses";
        public static final String EMAIL = "email";
    }

    /**
     * Constants for query
     */
    public static final class Query {
        private Query() {
            // do nothing
        }

        public static final String QUERY_STRING = "query";
        public static final String SPACE = " ";
        public static final String PARAMETERS = "paramameter";
        public static final String CONDITIONS = "condition";
        public static final String QUERY_SELECT = "query_select_column";
        public static final String QUERY_JOIN = "query_join";
        public static final String QUERY_GROUPBY = "query_groupby";
        public static final String QUERY_ORDERBY = "query_order_by";
        public static final String OFFSET_LIMIT = "offset_limit";
        public static final String INDEX_PARAM = "indexParam";
        public static final String IS_TRUE = "true";
        public static final String IS_FALSE = "false";
        public static final String SEARCH_LIKE = "LIKE";
        public static final String SEARCH_LIKE_FIRST = "LIKE ";
        public static final String SEARCH_OTHER = "<>";
        public static final String OR_CONDITION = "OR";
        public static final String SEARCH_OR_FLAG = "1";
        public static final String IS_NULL = " IS NULL ";
        public static final String EQUAL_EMPTY = " = '' ";
        public static final String AND_CONDITION = "AND";
        public static final String ALL_WORD = "WORD";
        public static final String NOT_AND_CONDITION = "NOT AND";
        public static final String NOT_OR_CONDITION = "NOT OR";
        public static final String REGEX_FIELD_NAME = "^[^0-9][a-zA-Z0-9_\\.]+";
        public static final String REGEX_FIELD_NAME_WITH_JSON = "^[^0-9][a-zA-Z0-9_]+(\\.[^0-9][a-zA-Z0-9_]+)+$";
        public static final String REGEX_DOT_ESCAPED = "\\.";

        public static final String LAMDA_OPERATOR = " ->> '";
        public static final String QUERY_PARAMETER_INDEX = " :indexParam";

        public enum SEARCH_FIELDS {
            FIELD_TYPE, FIELD_NAME, FIELD_VALUE, SEARCH_TYPE, SEARCH_OPTION, IS_DEFAULT, FROM, TO
        }

        /**
         * define special item map to get sort column
         */
        private static Map<String, String> sortColumnMap = new HashMap<>();

        static {
            sortColumnMap.put("language_id", "language_name");
            sortColumnMap.put("timezone_id", "timezone_name");
        }

        /**
         * get column for sort
         *
         * @param key
         * @return
         */
        public static String getSortColumn(String key) {
            return sortColumnMap.get(key);
        }
    }

    private Constants() {
    }

    /**
     * User does not have permission code
     */
    public static final String USER_NOT_PERMISSION = "ERR_COM_0007";

    /**
     * User does not have role admin
     */
    public static final String USER_HAVE_NOT_ROLE_ADMIN = "ERR_COM_039";

    /**
     * Product category has been registered product
     */
    public static final String CATEGORY_REGISTERED_PRODUCT = "ERR_PRO_0003";

    /**
     * Product category has product categories children
     */
    public static final String CATEGORY_HAS_CHILDREN = "ERR_PRO_0004";
    /**
     * 数に負の値は指定できません。
     */
    public static final String NUMBER_NOT_NEGATIVE = "ERR_COM_0016";

    /**
     * 更新を実行する前にデータが変更されました。再度ご確認ください。
     */
    public static final String EXCLUSIVE_CODE = "ERR_COM_0050";

    /**
     * 無効なパラメーター
     */
    public static final String INVALID_PARAMETER = "ERR_COM_0044";

    /**
     * Save to S3 failed
     */
    public static final String SAVE_FILE_TO_S3_FAILED = "ERR_COM_040";

    /**
     * File to S3 not exist.
     */
    public static final String FILE_NOT_EXIST = "ERR_COM_0042";

    /**
     * Delete file to S3 failed.
     */
    public static final String FILE_DELETE_FAILED = "ERR_COM_0041";

    /**
     * Total file size is too large
     */
    public static final String FILE_OVER_SIZE = "ERR_COM_0033";
    /**
     * last owner
     */
    public static final String GROUP_LAST_OWNER = "ERR_TIM_0002";

    /**
     * Total file size max. 2GB
     */
    public static final long FILE_SIZE_MAX = 2147483648L;

    public static final Object DECIMAL_PLACE_INVALID_CODE = "ERR_COM_0030";
    public static final Object LINK_INVALID_CODE = "ERR_COM_0055";

    /**
     * Contain index and number of variable in file part name in form-data
     * request's variables
     */
    public static final class PartNameSection {
        private PartNameSection() {

        }

        public static final int NUMBER_OF_SECTION = 3;

        public static final int ID_INDEX = 0;
        public static final int FIELD_NAME_INDEX = 1;
        public static final int FILE_ALIAS_INDEX = 2;

        public static final String SECTION_SPLIT_CHAR_REGEX = "\\.";
    }

    public enum PathEnum {
        EMPLOYEES,
        MAILS,
        COMMONS,
        SCHEDULES,
        PRODUCTS,
        CUSTOMERS,
        ACTIVITIES,
        TIMELINES,
        TENANTS,
        OCRS,
        BUSINESSCARDS,
        CHATS,
        EXTERNALS,
        SALES,
        TUTORIALS,
        ANALYSIS;
    }


    public static final int DELETED_FILE_STATUS = 1;

    public static final String OWNER = "ERR_TIM_0001";
    /**
     * Name of micro Service employees
     */
    public static final String MICRO_SERVICE_EMPLOYEES = "employees";

    /**
     * Name of micro Service customers
     */
    public static final String MICRO_SERVICE_CUSTOMERS = "customers";

    /**
     * Name of micro Service business cards
     */
    public static final String MICRO_SERVICE_BUSINESS_CARDS = "businesscards";

    /**
     * Name of micro Service products
     */
    public static final String MICRO_SERVICE_PRODUCTS = "products";

    /**
     * Name of micro Service commons
     */
    public static final String MICRO_SERVICE_COMMONS = "commons";

    /**
     * Name of micro Service schedules
     */
    public static final String MICRO_SERVICE_SCHEDULES = "schedules";


    /**
     * Name of micro Service tenants
     */
    public static final String MICRO_SERVICE_TENANTS = "tenants";

    /**
     * Name of micro Service uaa
     */
    public static final String MICRO_SERVICE_UAA = "uaa";

    /**
     * Name of micro Service activities
     */
    public static final String MICRO_SERVICE_ACTIVITIES = "activities";

    /**
     * Name of micro Service sales
     */
    public static final String MICRO_SERVICE_SALES = "sales";

    /**
     * Name of micro Service timelines
     */
    public static final String MICRO_SERVICE_TIMELINES = "timelines";

    /**
     * Service type
     */
    public enum ServiceType {
        ACTIVITIES(3);

        private int value;

        private ServiceType(int value) {
            this.value = value;
        }

        public int getValue() {
            return value;
        }
    }

    /**
     * Transaction management service contants
     */
    public static final class TMS {
        private TMS() {
            //initial only
        }

        public static final String ACTION = "Action";
        public static final String ACTION_START_TRANSACTION = "StartTransaction";
        public static final String ACTION_END_TRANSACTION = "EndTransaction";
        public static final String ACTION_EXECUTE = "Execute";
        public static final String ACTION_REGISTER_PS = "RegisterPS";

        public static final String PS_LIST = "PSList";
        public static final String PS_NAME = "PSName";
        public static final String PS_ARGS = "PSArgs";
        public static final String PS_TEMPLATE = "PSTemplate";

        public static final String TIMEOUT = "Timeout";
        public static final String TRANS_ID = "TransID";
        public static final String QUERIES = "Queries";
        public static final String CODE = "Code";
        public static final String EXCEPTION = "Exception";
        public static final String REASON = "Reason";
        public static final String MSG = "Msg";
        public static final String CODE_SUCCESS = "1";
        public static final String CODE_FAILED = "0";
        public static final String CODE_INCOMPLETE = "2";

        public static final String SINGLE_QUOTE = "'";
        public static final String RESULTS = "Results";

        public static final String ACTION_ROLLBACK_TRANSACTION = "RollbackTransaction";

        public static final String TMS_SERVICE_EXCEPTION = "TmsService exception";

        public static final String TMS_IO_EXCEPTION = "ERR_TMS_001";

        public static final String TMS_UNEXPECTED_EXCEPTION = "ERR_TMS_999";

    }

    public enum TimelineAuto {
        EMPLOYEE(1, -1, -1, FieldBelong.EMPLOYEE.getValue()), COMPANY(2, -1, -1, -1), DEPARTMENT(3, -1, -1, -1),
        CHANNEL(4, 2, 2, -1), CUSTOMER(5, 3, 4, FieldBelong.CUSTOMER.getValue()), BUSINESS_CARD(6, 4, 5, FieldBelong.BUSINESS_CARD.getValue()),
        ACTIVITY(7, 5, 3, FieldBelong.ACTIVITY.getValue()), SCHEDULE(8, 6, 6, FieldBelong.SCHEDULE.getValue()),
        TASK(9, 7, 7, FieldBelong.TASK.getValue()), MILESTONE(10, 8, 8, FieldBelong.MILESTONE.getValue()),
        IMPORT(-1, 9, 1, -1), BATCH(1, 10, 9, -1);
        private Integer targetType;
        private Integer createPosition;
        private Integer timelineType;
        private Integer headerType;

        private TimelineAuto(Integer targetType, Integer createPosition, Integer timelineType, Integer headerType) {
            this.targetType = targetType;
            this.createPosition = createPosition;
            this.timelineType = timelineType;
            this.headerType = headerType;
        }

        public Integer getTargetType() {
            return targetType;
        }

        public Integer getCreatePosition() {
            return createPosition;
        }

        public Integer getTimelineType() {
            return timelineType;
        }

        public Integer getHeaderType() {
            return headerType;
        }

        public static final String INVITE_ID = "inviteId";
        public static final String INVITE_TYPE = "inviteType";
    }

    // TMS constants
    public static final String SELECT = " SELECT ";
    public static final String DELETE = " DELETE ";
    public static final String FROM = " FROM ";
    public static final String WHERE = " WHERE ";
    public static final String LEFT_JOIN = " LEFT JOIN ";
    public static final String INNER_JOIN = " INNER JOIN ";
    public static final String ORDER_BY = " ORDER BY ";
    public static final String GROUP_BY = " GROUP BY ";
    public static final String AS = " AS ";

}
