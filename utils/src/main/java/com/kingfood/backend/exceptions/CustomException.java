package com.kingfood.backend.exceptions;


import com.kingfood.backend.constants.Constants;

import java.util.*;


public class CustomException extends RuntimeException {

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 1360714657241683530L;

    private static final String ERRORS_NAME = "errors";

    /**
     * The extensions
     */
    private Map<String, Object> attributes;

    public CustomException(String message) {
        super(message);
    }

    public CustomException(String message, Throwable cause) {
        super(message, cause);
    }

    public CustomException(Throwable cause) {
        super(cause);
    }

    /**
     * Constructor
     *
     * @param message the message error
     * @param item item error
     * @param errorCode error code
     */
    public CustomException(String message, String item, String errorCode) {
        super(message);
        addError(item, errorCode, null, null, null);
    }

    /**
     * Constructor
     *
     * @param message the message error
     * @param item item error
     * @param errorCode error code
     */
    public CustomException(String message, String item, String errorCode, String[] errorParams) {
        super(message);
        addError(item, errorCode, null, errorParams, null);
    }

    /**
     * Constructor
     *
     * @param message the message error
     * @param item item error
     * @param errorCode error code
     */
    public CustomException(String message, String item, String errorCode, String[] errorParams, Long[] rowIds) {
        super(message);
        addError(item, errorCode, null, errorParams, rowIds);
    }

    /**
     * Constructor
     *
     * @param message the message error
     * @param item item error
     * @param errorCode error code
     * @param rowId rowId error
     */
    public CustomException(String message, String item, String errorCode, Long rowId) {
        super(message);
        addError(item, errorCode, rowId, null, null);
    }

    /**
     * Constructor
     *
     * @param message the message error
     * @param item item error
     * @param errorCode error code
     * @param rowIds rowIds error
     */
    public CustomException(String message, String item, String errorCode, Long[] rowIds) {
        super(message);
        addError(item, errorCode, null, null, rowIds);
    }

    /**
     * Constructor
     *
     * @param message the message error
     * @param item item error
     * @param errorCode error code
     */
    public CustomException(String message, String item, String errorCode, Long rowId, String[] params) {
        super(message);
        addError(item, errorCode, rowId, params, null);
    }

    /**
     * Constructor
     *
     * @param message the message error
     * @param errorsItem Map error
     */
    public CustomException(String message, Map<String, Object> errorsItem) {
        super(message);
        this.attributes = new LinkedHashMap<>();
        List<Map<String, Object>> errList = new ArrayList<>();
        if (errorsItem != null && errorsItem.get(Constants.ROW_ID) != null) {
            errorsItem.put(Constants.ROW_ID, Double.valueOf(errorsItem.get(Constants.ROW_ID).toString()).longValue());
        }
        Map<String, Object> messageErr = new HashMap<>();
        messageErr.put("message", message);
        errList.add(errorsItem);
        attributes.put("message", messageErr);
        attributes.put(ERRORS_NAME, errList);
    }

    /**
     * Constructor
     *
     * @param message the message error
     * @param errorsItems Map error
     */
    public CustomException(String message, List<Map<String, Object>> errorsItems) {
        super(message);
        this.attributes = new LinkedHashMap<>();
        if (errorsItems != null) {
            for (Map<String, Object> item : errorsItems) {
                if (item.get(Constants.ROW_ID) != null) {
                    item.put(Constants.ROW_ID, Double.valueOf(item.get(Constants.ROW_ID).toString()).longValue());
                }
            }
        }
        attributes.put(ERRORS_NAME, errorsItems);
    }

    /**
     * Add an error to attributes
     *
     * @param item item error
     * @param errorCode error code
     * @param rowId rowIds error
     * @param errorParams errorParams
     */
    private void addError(String item, String errorCode, Long rowId, String[] errorParams, Long[] rowIds) {
        this.attributes = new LinkedHashMap<>();
        List<Map<String, Object>> errList = new ArrayList<>();
        Map<String, Object> err = new HashMap<>();
        err.put(Constants.ERROR_ITEM, item);
        err.put(Constants.ERROR_CODE, errorCode);
        if (rowId != null) {
            err.put(Constants.ROW_ID, rowId);
        }
        if (rowIds != null && rowIds.length > 0) {
            err.put(Constants.ROW_ID, rowIds);
        }
        if (errorParams != null && errorParams.length > 0) {
            err.put(Constants.ERROR_PARAMS, errorParams);
        }
        errList.add(err);
        attributes.put(ERRORS_NAME, errList);
    }

    public CustomException() {

    }

    /**
     * @See graphql.GraphQLError.getExtensions()
     */
    public Map<String, Object> getExtensions() {
        return this.attributes;
    }

    public void setExtensions(Map<String, Object> attributes) {
        this.attributes = attributes;
    }
}