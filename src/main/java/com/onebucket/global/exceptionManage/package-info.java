/**
 * This package provide Exception, ExceptionHandler, ExceptionResponse, etc... <br>
 * <p>
 *   Use Define errorCode, describe on {@link com.onebucket.global.exceptionManage.errorCode errorCode} package. <br>
 *   Also handle error by catching exception from servlet on {@link com.onebucket.global.exceptionManage.exceptionHandler exceptionHandler} package.<br>
 *   Redefine, or custom exceptions are in {@link com.onebucket.global.exceptionManage.exceptions exceptions} package.<br>
 * </p>
 * <hr><hr>
 * <h1>1. error code</h1>
 * Other enum type error codes are implements {@link com.onebucket.global.exceptionManage.errorCode.ErrorCode errorCode} interface.
 * Basically, it includes <strong>name, code, status, message, type</strong> <br>
 * The numerical codes for these error codes follow a specific pattern as described below:
 *
 * <table border="errorCode">
 *     <tr>
 *         <th><span style="color: white;">series</span></th>
 *         <th><span style="color: white;">describe</span></th>
 *     </tr>
 *     <tr>
 *         <th>1000 series</th>
 *         <th>Exceptions related to user, security, authentication, and authorization issues.</th>
 *     </tr>
 *     <tr>
 *         <th>2000 series</th>
 *         <th>General exceptions, such as invalid parameters or database errors.</th>
 *     </tr>
 *     <tr>
 *         <th>3000 series</th>
 *         <th>Specific database exceptions.</th>
 *     </tr>
 * </table> <br>
 * <hr>
 *
 * <h2>1.1 UserErrorCode</h2>
 * Provide error code with userDetails status, or signIn Exception.
 * <table border= "errorCode">
 *      <tr>
 *          <th>name</th>
 *          <th>status</th>
 *          <th>code</th>
 *      </tr>
 * </table>
 *
 */
package com.onebucket.global.exceptionManage;