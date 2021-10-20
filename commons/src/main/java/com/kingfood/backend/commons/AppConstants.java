package com.kingfood.backend.commons;

public interface AppConstants {
    interface MESSAGE {
        interface DEPARTMENT_API {
            String SAVE_SUCCESS = "SAVE DEPARTMENT SUCCESSFULLY";
            String UPDATE_SUCCESS = "UPDATE DEPARTMENT SUCCESSFULLY";
            String DELETE_SUCCESS = "DELETE DEPARTMENT SUCCESSFULLY";
            String FIND_SUCCESS = "FIND DEPARTMENT SUCCESSFULLY";
        }

        interface API_API {
            String SAVE_SUCCESS = "SAVE API SUCCESSFULLY";
            String UPDATE_SUCCESS = "UPDATE API SUCCESSFULLY";
            String DELETE_SUCCESS = "DELETE API SUCCESSFULLY";
            String GET_ALL_SUCCESSFULLY = "GET ALL API SUCCESSFULLY";
        }
        interface ROLE_API {
            String GET_ALL_SUCCESSFULLY = "GET ALL ROLE SUCCESSFULLY";
        }
        interface ERROR {
            String FAILED_CREATE = "failed to create";
            String FAILED_UPDATE = "failed to update";
            String BRANCH_NOT_FIND = "BRANCH_NOT_FIND";

            interface ERROR_THROW {
                interface API {
                    String ROLE_NOT_FOUND = "role not found";
                }
            }
        }
        interface BRANCH_API {
            String SAVE_SUCCESS = "SAVE BRANCH SUCCESSFULLY";
            String UPDATE_SUCCESS = "UPDATE BRANCH SUCCESSFULLY";
            String FIND_SUCCESS = "FIND BRANCH SUCCESSFULLY";
            String DELETE_SUCCESS = "DELETE BRANCH SUCCESSFULLY";
            String GET_ALL_SUCCESSFULLY = "GET ALL BRANCH SUCCESSFULLY";
        }
    }
}
