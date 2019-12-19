import 'package:test/test.dart';
import 'package:otpauth/otpauth.dart';

void main() {
  test('otp auth', () {
    expect(buildOTP("Example", "alice@google.com", "JBSW Y3DP EHPK 3PXP"),
        "otpauth://totp/Example:alice@google.com?secret=JBSWY3DPEHPK3PXP&issuer=Example");

    expect(
        buildOTP("ACME Co", "john.doe@email.com",
            "HXDM VJEC JJWS RB3H WIZR 4IFU GFTM XBOZ"),
        "otpauth://totp/ACME%20Co:john.doe@email.com?secret=HXDMVJECJJWSRB3HWIZR4IFUGFTMXBOZ&issuer=ACME%20Co");
  });
}
