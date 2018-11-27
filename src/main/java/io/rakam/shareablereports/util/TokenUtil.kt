package io.rakam.shareablereports.util

import com.auth0.jwt.JWT
import com.auth0.jwt.algorithms.Algorithm
import com.auth0.jwt.exceptions.JWTCreationException
import io.rakam.shareablereports.dto.ShareReportRequestDTO
import java.util.*

object TokenUtil {

    private const val secretKeyConst: String = "secret"
    private const val reportIdConst: String = "reportId"
    private const val issuerConst: String = "auth0"
    private const val editableOptionsConst: String = "editableOptions"



    @Throws(JWTCreationException::class)
    fun createToken(expirationSecond: Long, reportId: Long, editableOptions: Array<String>): String {
        val algorithm = Algorithm.HMAC256(secretKeyConst)
        return JWT.create().withIssuer(issuerConst)
                           .withIssuedAt(Date())
                           .withExpiresAt(Date(System.currentTimeMillis() + expirationSecond))
                           .withClaim(reportIdConst, reportId)
                           .withArrayClaim(editableOptionsConst, editableOptions).sign(algorithm)
    }

    @Throws(JWTCreationException::class)
    fun decodeToken(token: String): JWTTokenInfo {
        val algorithm = Algorithm.HMAC256(secretKeyConst)
        var verifier = JWT.require(algorithm).withIssuer(issuerConst).build()
        var jwt = verifier.verify(token)

        var reportId = jwt.getClaim(reportIdConst).asLong()
        var editableOptions = jwt.getClaim(editableOptionsConst).asArray(String::class.java)

        return JWTTokenInfo(editableOptions, reportId)
    }
}

data class JWTTokenInfo(val editableOptions: Array<String>, val reportId: Long)
