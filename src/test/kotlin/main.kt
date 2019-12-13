package otpauth

import kotlin.test.Test
import kotlin.test.assertEquals

class TestOTPAuth() {
    @Test
    fun build() {
        assertEquals(
            buildOTP("Google Mail", "user@example.com", "abcd efgh ijkl mnop"),
            "otpauth://totp/Google%20Mail:user@example.com?issuer=Google%20Mail&secret=abcdefghijklmnop"
        )
    }
}
