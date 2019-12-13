package otpauth

import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel
import com.google.zxing.qrcode.encoder.Encoder
import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.mainBody
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

class OTPAuthArgs(parser: ArgParser) {
    val issuer by parser.storing("-i", "--issuer", help = "eg: Google / Github / Cloudflare / ...")
    val account by parser.storing("-a", "--account", help = "eg: user@example.com")
    val secret by parser.storing("-s", "--secret", help = "OTP key. eg: abcd efgh ijkl mnop")
}

fun buildOTP(oissuer: String, account: String, osecret: String): String {
    val issuer = URLEncoder.encode(oissuer, StandardCharsets.UTF_8).replace("+", "%20")
    val secret = osecret.replace(" ", "")
    // https://github.com/google/google-authenticator/wiki/Key-Uri-Format
    val label = "$issuer:$account"
    val parameters = "issuer=$issuer&secret=$secret"
    val otp = "otpauth://totp/$label?$parameters"
    return otp
}

fun toTerminalString(matrix: Array<ByteArray>): String {
    // https://superuser.com/a/1420015
    val black = "\u001b[40m  \u001b[0m"
    val white = "\u001b[47m  \u001b[0m"

    val zeroByte: Byte = 0
    val s = matrix.map { row ->
        row.map { if (it == zeroByte) white else black }.joinToString("")
    }.joinToString("\n")

    return s
}

fun main(args: Array<String>) = mainBody {
    val parsedArgs = ArgParser(args).parseInto(::OTPAuthArgs)
    parsedArgs.let {
        val otp = buildOTP(it.issuer, it.account, it.secret)
        val qr = Encoder.encode(otp, ErrorCorrectionLevel.L)
        val str = toTerminalString(qr.getMatrix().getArray())
        println(str)
    }
}
