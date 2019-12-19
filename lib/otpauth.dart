import 'dart:core';
import 'package:qr/qr.dart';

String buildOTP(String issuer, String account, String secretSpace) {
  // https://github.com/google/google-authenticator/wiki/Key-Uri-Format
  var secret = secretSpace.replaceAll(" ", "");
  var otp = Uri.encodeFull(
      "otpauth://totp/$issuer:$account?secret=$secret&issuer=$issuer");
  return otp;
}

String toImage(String otp) {
  var code =
      QrCode.fromData(data: otp, errorCorrectLevel: QrErrorCorrectLevel.L)
        ..make();

  // https://superuser.com/a/1420015
  var black = "\u001b[40m  \u001b[0m";
  var white = "\u001b[47m  \u001b[0m";

  var img = new StringBuffer();
  for (var i = 0; i < code.moduleCount; i++) {
    for (var j = 0; j < code.moduleCount; j++) {
      if (code.isDark(j, i)) {
        img.write(black);
      } else {
        img.write(white);
      }
    }
    img.write('\n');
  }

  return img.toString();
}
