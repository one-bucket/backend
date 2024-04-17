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
 *         <th><span style="color: orange;">series</span></th>
 *         <th><span style="color: orange;">describe</span></th>
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
 *
 * <h2>1.1 [1000] series error code</h2>
 * <br>
 * <h3>1.1.1 UserErrorCode</h3>
 * Provide error code with userDetails status, or signIn Exception.
 * <!--userErrorCode 코드 테이블-->
 * <table border= "userErrorCode";">
 *      <tr>
 *          <th><span style="color: orange;">name</span></th>
 *          <th><span style="color: orange;">code</span></th>
 *          <th><span style="color: orange;">status</span></th>
 *      </tr>
 *      <tr>
 *          <th>LOCK_USER</th>
 *          <th>1001</th>
 *          <th>forbidden(403)</th>
 *      </tr>
 *      <tr>
 *          <th>EXPIRED_USER</th>
 *          <th>1002</th>
 *          <th>forbidden(403)</th>
 *      </tr>
 *      <tr>
 *          <th>BANNED_USER</th>
 *          <th>1003</th>
 *          <th>forbidden(403)</th>
 *      </tr>
 *      <tr>
 *          <th>USERNAME_PASSWORD<br>_INVALID</th>
 *          <th>1004</th>
 *          <th>unauthorized(401)</th>
 *      </tr>
 *      <tr>
 *          <th>CREDENTIALS_EXPIRED</th>
 *          <th>1005</th>
 *          <th>unauthorized(401)</th>
 *      </tr>
 *      <tr>
 *          <th>UNSUPPORTED<br>_ACCOUNT_EXCEPTION</th>
 *          <th>1099</th>
 *          <th>internal server error(500)</th>
 *      </tr>
 * </table>
 * <!--에러 코드 describe-->
 * <pre>
- 1001 : locked in UserDetails by authorization or security issue.
- 1002 : user should update password or else.
- 1003 : banned user by admin or security issue.
- 1004 : incorrect password or username.
- 1005 : user has expired account.
- 1099 : unsupported internal server error.
 * </pre> <br>
 * <hr>
 * <h3>1.1.2 JwtErrorCode</h3>
 * Provide error code with jwt token, for refresh or access denied protocol in client. Its for {@link com.onebucket.global.auth.jwtAuth.filter.JwtAuthenticationFilter JwtAuthenticationFilter}
 * to make response entity with json, not for handling in exceptionHandler. So, just provide
 * <strong>numeric code, name and type(JWT_ERROR)</strong>, and no HttpStatus.
 * <!-- JwtErrorCode 코드 테이블-->
 * <table border= "JwtErrorCode";">
 *      <tr>
 *          <th><span style="color: orange;">name</span></th>
 *          <th><span style="color: orange;">code</span></th>
 *          <th><span style="color: orange;">status</span></th>
 *      </tr>
 *      <tr>
 *          <th>EXPIRED_TOKEN</th>
 *          <th>1100</th>
 *          <th>null</th>
 *      </tr>
 *      <tr>
 *          <th>INVALID_SIGNATURE_TOKEN</th>
 *          <th>1101</th>
 *          <th>null</th>
 *      </tr>
 *      <tr>
 *          <th>INVALID_CLAIM_TOKEN</th>
 *          <th>1102</th>
 *          <th>null</th>
 *      </tr>
 *      <tr>
 *          <th>INVALID_FORM_TOKEN</th>
 *          <th>1103</th>
 *          <th>null</th>
 *      </tr>
 *      <tr>
 *          <th>AUTHORITY_NOT_FOUND_TOKEN</th>
 *          <th>1104</th>
 *          <th>null</th>
 *      </tr>
 *      <tr>
 *          <th>UNSUPPORTED_JWT_ERROR</th>
 *          <th>1199</th>
 *          <th>null</th>
 *      </tr>
 * </table> <br>
 * <hr>
 * <h3>1.1.3 RegisterErrorCode</h3>
 * Provide error when user try to register. Bad request, or else like verified email or create profile.
 * Type of this error is <strong>REGISTER_ERROR</strong>.
 * <!-- RegisterErrorCode 코드 테이블-->
 * <table border= "RegisterErrorCode";">
 *      <tr>
 *          <th><span style="color: orange;">name</span></th>
 *          <th><span style="color: orange;">code</span></th>
 *          <th><span style="color: orange;">status</span></th>
 *      </tr>
 *      <tr>
 *          <th>UNSUPPORTED_SCHOOL</th>
 *          <th>1200</th>
 *          <th>bad request(400)</th>
 *      </tr>
 *      <tr>
 *          <th>INVALID_EMAIL_FORM</th>
 *          <th>1201</th>
 *          <th>bad request(400)</th>
 *      </tr>
 *      <tr>
 *          <th>INVALID_VERIFIED_CODE</th>
 *          <th>1103</th>
 *          <th>bad request(400)</th>
 *      </tr>
 *      <tr>
 *          <th>UNSUPPORTED_VERIFIED_ERROR</th>
 *          <th>1199</th>
 *          <th>internal server error(500)</th>
 *      </tr>
 * </table> <br>
 * <!--error code describe-->
 * <pre>
 1200 : no school in database.
 1201 : no matching with school and email.
 1202 : invalid code.
 1299 : unsupported error.
 * </pre>
 * <hr>
 * <h2>1.2 [2000] series error code</h2>
 * <br>
 * <h3>1.2.1 CommonErrorCode</h3>
 * Provide error code caused by communication with client and server, notFound, else
 * Type is <strong>COMMON_ERROR</strong>.
 * <table border= "RegisterErrorCode";">
 *      <tr>
 *          <th><span style="color: orange;">name</span></th>
 *          <th><span style="color: orange;">code</span></th>
 *          <th><span style="color: orange;">status</span></th>
 *      </tr>
 *      <tr>
 *          <th>INVALID_PARAMETER</th>
 *          <th>2001</th>
 *          <th>bad request(400)</th>
 *      </tr>
 *      <tr>
 *          <th>RESOURCE_NOT_FOUND</th>
 *          <th>2002</th>
 *          <th>not found(404)</th>
 *      </tr>
 *      <tr>
 *          <th>INTERNAL_SERVER_ERROR</th>
 *          <th>2003</th>
 *          <th>internal server error(500)</th>
 *      </tr>
 * </table> <br>
 *  <hr>
 */
package com.onebucket.global.exceptionManage;