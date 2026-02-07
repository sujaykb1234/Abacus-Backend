package com.abacus.franchise.model;

public final class Messages {

    private Messages() {
        // Prevent instantiation
    }

    // =========================
    // GENERAL / COMMON
    // =========================
    public static final String franchies_not_found = "FRANCHIES NOT FOUND..!";
    public static final String franchies_delete_succesfully = "FRANCHIES DELETE SUCCESFULLY..!";
    public static final String nullData = "YOU ENTER NULL DATA PLZ CHECK..!";
    public static final String nullField = "YOU ENTER THE NULL FIELD PLZ CHECK..!";
    public static final String idnotFound = "ID NOT FOUND..!";
    public static final String dataNotFound = "INFORMATION NOT FOUND..!";
    public static final String record_not_found = "FRANCHISE KIT REQUEST NOT FOUND FOR THIS COURSE..!";

    // =========================
    // EMAIL
    // =========================
    public static final String EmailNotSend = "THIS EMAIL NOT FOUND. PLEASE ENTER REGISTERED EMAIL ADDRESS...!";
    public static final String EmailSendSuccesfully = "EMAIL SEND SUCCESFULLY..!";
    public static final String emailNotFound = "EMAIL ID NOT FOUND..!";
    public static final String email_no_already_exist = "EMAIL ADDRESS ALREADY EXIST..!";

    // =========================
    // AUTH / LOGIN
    // =========================
    public static final String admilogin = "LOGIN SUCCESSFULLY";
    public static final String nullUserNamePass = "YOU ENTER NULL USERNAME OR PASSWORD PLZ CHECK..!";
    public static final String wrongPassword = "ENTER CORRECT PASSWORD";
    public static final String incorrectUserName = "INCORRECT USER NAME PLZ CHECK..!";
    public static final String userNotFound = "USER NOT FOUND TRY AGAIN..!";
    public static final String passwordNull = "YOU ENTER THE NULL PASSWORD PLZ CHECK..!";
    public static final String passwordLenght = "PLZ CHECK THE PASSWORD LENGTH..!";
    public static final String passwordUpdateSuccesfully = "PASSWORD UPDATE  SUCCESFULLY..!";

    // =========================
    // ADMIN
    // =========================
    public static final String adminRegister = "ADMIN INFORMATION SAVE SUCCESFULLY..!";
    public static final String adminUpdate = "ADMIN INFORMATION UPDATE SUCCESFULLY..!";
    public static final String imageError = "EXEPTION OCCURE PLZ CHECK..!";

    // =========================
    // FRANCHISE
    // =========================
    public static final String franchise_not_found = "FRANCHISE NOT FOUND..!";
    public static final String franchiesNotFound = "FRANCHIES NOT FOUND..!";
    public static final String franchiseSave = "FRANCHISE DATA SAVE SUCCESFULLY..!";
    public static final String franchiseupdate = "FRANCHISE UPDATE SUCCESFULLY..!";
    public static final String franchiseLogin = "FRANCHISE LOGIN SUCCESFULLY..!";
    public static final String franchies_get_succesfully = "FRANCHIES INFORMATION GET SUCCESFULLY..!";
    public static final String franchies_not_authenticated = "WAIT TILL APPROVE YOUR REQUEST FROM ADMIN..!";
    public static final String franchies_requests = "FRANCHISE REQUESTS..!";
    public static final String first_delet_student = "CANNOT DELETE FRANCHISE WITH ACTIVE STUDENTS. PLEASE DELETE STUDENTS FIRST..!";
    public static final String first_delet_student_course = "CANNOT DELETE COURSE WITH ACTIVE STUDENTS. PLEASE DELETE STUDENTS FIRST..!";

    // =========================
    // STUDENT
    // =========================
    public static final String studentSave = "STUDENT INFORMATION SAVE SUCCESFULLY..!";
    public static final String studentUpdate = "STUDENT UPDATE SUCCESFULLY..!";
    public static final String studentDelete = "STUDENT DELETED SUCCESSFULLY..!";
    public static final String studentLoginSuccesfully = "STUDENT LOGIN SUCCESFULLY..!";
    public static final String studentGetSuccesfully = "STUDENT INFORMATION GET SUCCESFULLY..!";
    public static final String student_get_succesfully = "STUDENT INFORMATION GET SUCCESFULLU..!";
    public static final String student_not_found = "STUDENT NOT FOUND....!";
    public static final String studentNotFound = "STUDENT NOT FOUND..!";
    public static final String no_students_found = "NO STUDENTS FOUND..!";
    public static final String getStudents = "STUDENTS RETRIVED..!";
    public static final String student_count = "GET STUDENTS COUNTS SUCCESFULLY..!";
    public static final String alreadyEnrolled = "STUDENT IS ALREADY ENROLLED IN THIS COURSE..!";
    public static final String alreadyInCourseStudent = "STUDENT IS ALREADY  IN THE COURSE..!";
    public static final String studentAreNotHavingCourse = "IT SEEMS LIKE YOUR ARE NOT ENROLLED WITH THIS COURSE..!";
    public static final String student_exam_retrived = "EXAM STUDENTS RETRIVED..!";
    public static final String student_not_found_for_given_student_id = "NO STUDENTS FOUND FOR THE PROVODED STUDENTS IDs...!";

    // =========================
    // COURSE
    // =========================
    public static final String courseNotFound = "COURSE NOT FOUND..!";
    public static final String course_not_found = "NO COURSE FOUND ..!";
    public static final String courseSaveSuccesfully = "COURSE SAVE SUCCESFULLY..!";
    public static final String courseUpdateSuccesfully = "COURSE UPDATE SUCCESFULLY..!";
    public static final String courseGetSuccesfully = "ALL COURSE GET SUCCESFULLY..!";
    public static final String course_get_succesfully = "COURSE INFORMATION GET SUCCESFULLY..!";
    public static final String course_delete_succesfully = "COURSE  DELETE SUCCESFULLY..!";
    public static final String course_already_deleted = "COURSE ALREADY DELETED..!";
    public static final String course_assign = "COURSE ASSIGN SUCCESFULLY..!";
    public static final String course_removed = "COURSE REMOVED SUCCESFULLY..!";
    public static final String course_retrieved = "COURSE RETRIEVED SUCCESFULLY..!";
    public static final String courseAlreadyAssign = "COURSE IS ALREADY ASSIGN FOR FRANCHIES..!";
    public static final String course_not_assign_to_this_students = "THE EXAM DOES NOT BELONGS TO THE STUDENT.";
    public static final String course_not_found_for_this_student = "COURSE_NOT_FOUND_FOR_THIS_STUDENT..!";
    public static final String request_for_course = "REQUEST TO ADMIN FOR ASSIGN COURSE..!";
    public static final String previous_course_not_complete = "THEIR PREVIOUS COURSE IS NOT COMPLETE..!";
    public static final String this_course_already_complete = "THIS COURSE IS ALREADY COMPLETE..!";

    // =========================
    // EXAM
    // =========================
    public static final String questions_save = "QUESTION SAVE SUCCESFULLY..!";
    public static final String questions_update = "QUESTION UPDATE SUCCESFULLY..!";
    public static final String questions_found = "QUESTION FOUND SUCCESFULLY..!";
    public static final String questions_not_found = "QUESTION NOT FOUND....!";
    public static final String exam_save = "EXAM SAVE SUCCESFULLY..!";
    public static final String exam_not_found = "EXAM NOT FOUND....!";
    public static final String exam_submit = "EXAM SUBMIT SUCCESFULLY..!";
    public static final String exam_set_to_Student = "EXAM SET TO STUDENT SUCCESSFULLY..!";
    public static final String exam_already_assign = "EXAM_ALREADY_ASSIGN..!";
    public static final String exam_created = "EXAM CREATED FOR THE COURSE..!";
    public static final String exam_not_create = "EXAM NOT CREATED FOR THE COURSE..!";
    public static final String Exam_not_found_student = "EXAM NOT FOUND FOR STUDENT..!";

    // =========================
    // PRACTICE / OFFLINE EXAM
    // =========================
    public static final String noPractiseTests = "NO PRACTICE TESTS FOUND FOR THIS STUDENT..!";
    public static final String practiceTests = "PRACTICE TESTS RETRIVED FOR STUDENT..!";
    public static final String OFFLINE_EXAM_SAVE_SUCCESS = "OFFLINE EXAM SAVE SUCCESSFULLY..!";
    public static final String OFFLINE_EXAM_NOT_FOUND = "OFFLINE EXAM NOT FOUND..!";
    public static final String OFFLINE_EXAM_FOUND = "OFFLINE EXAM RETRIEVED SUCCESSFULLY..!";
    public static final String OFFLINE_EXAM_DELETE_SUCCESS = "OFFLINE EXAM DELETED SUCCESSFULLY..!";

    // =========================
    // KIT / ORDER
    // =========================
    public static final String kit_order_placced = "KIT ORDER PLACED SUCCESSFULLY..!";
    public static final String kit_count = "NO STUDENT PRESENT IN THIS COURSE..!";
    public static final String noKitRequest = "NO KIT REQUESTS FOUND..!";
    public static final String kitReq = "KIT REQUESTS..!";
    public static final String insufficientKit = "INSUFFICIENT KIT COUNT..!";
    public static final String orderKits = "NO KIT ORDERED FOR THIS COURSE ORDER KITS FIRST..!";
    public static final String kitSendSuccessfully = "KITS SEND TO FRANCHISE SUCCESSFULLY..!";
    public static final String noKitsAvaiable = "NO ENOUFG KITS ARE AVAILABLE..!";
    public static final String kit_request_not_found = "KIT REQUEST NOT FOUND..!";
    public static final String kit_request_get_succesfully = "KIT REQUESTS GET SUCCESFULLY..!";

    // =========================
    // CERTIFICATE
    // =========================
    public static final String certificate_saved = "CERTIFICATE SAVED SUCCESSFULLY..!";
    public static final String certificate_updated = "CERTIFICATE UPDATED SUCCESSFULLY..!";
    public static final String certificate_deleted = "CERTIFICATE DELETED SUCCESSFULLY..!";
    public static final String certificate_not_found = "CERTIFICATE NOT FOUND..!";
    public static final String certificates_retrieved = "CERTIFICATES RETRIEVED SUCCESSFULLY..!";

    // =========================
    // IMAGE
    // =========================
    public static final String IMAGE_NOT_FOUND = "IMAGE NOT FOUND";
    public static final String IMAGE_FOUND = "IMAGE RETRIEVE SUCCESSFULLY..!";
    public static final String IMAGE_DELETE = "IMAGE DELETE SUCCESSFULLY..!";
    public static final String IMAGE_SAVE = "IMAGE SAVE SUCCESSFULLY..!";
    public static final String exception_for_img = "EXECPTION FOR IMAGE..!";
    public static final String change_dawnload_status = "MARK AS DOWNLOAD SUCCESSFULLY..!";

    // =========================
    // SCORE / RESULT
    // =========================
    public static final String course = "SCORE";
    public static final String correct_answers = "CORRECT_ANSWERS";
    public static final String total_questions = "TOTAL_QUESTIONS";
    public static final String student_id = "STUDENT_ID";

    // ======================
    // Other / Result
    // ======================
    public static final String mobile_no_already_exist = "MOBILE NUMBER ALREADY EXIST PLZ CHECK..!";
    public static String imageNull = "IMAGE IS REQUIRED";
    public static String nameIsNull = "YOU ENTER THE NULL NAME..!";
    public static String nameIsWrong = "YOU ENTER THE WRONG NAME PLZ CHECK..!";
    public static String save_state = "STATE SAVE SUCCESFULLY..!";
    public static String update_state = "STATE UPDATE SUCCESFULLY..!";
    public static String state_not_found = "STATE NOT FOUND..!";
    public static String state_found = "STATE FOUND SUCCESFULLY..!";
    public static String save_district = "DISTRICT SAVE SUCCESFULLY..!";
    public static String update_district = "DISTRICT UPDATE SUCCESFULLY..!";
    public static String district_not_found = "DISTRICT NOT FOUND..!";
    public static String district_found = "DISTRICT FOUND SUCCESFULLY..!";
    public static String save_taluka = "TALUKA SAVE SUCCESFULLY..!";
    public static String update_taluka = "TALUKA UPDATE SUCCESFULLY..!";
    public static String taluka_not_found = "TALUKA NOT FOUND..!";
    public static String taluka_found = "TALUKA FOUND SUCCESFULLY..!";
    public static String getTheAllExam = "EXAM INFORMATION GET SUCCESFULLY..!";
    public static String examForStudent = "EXAM RETRIVED FOR STUDENT..!";
    public static String notificationsRetrived = "NOTIFICATIONS..!";
    public static String notificationsNotFound = "NO NOTIFICATIONS FOUND..!";


}
