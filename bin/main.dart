import 'package:args/args.dart';
import 'package:otpauth/otpauth.dart';

void main(List<String> args) {
  final parser = ArgParser()
    ..addOption("issuer", abbr: "i", help: "(required) eg: 'Google Mail'")
    ..addOption("account", abbr: "a", help: "(required) eg: 'user@example.com'")
    ..addOption("secret",
        abbr: "s", help: "(required) eg: 'abcd efgh ijkl mnop'")
    ..addFlag("help", abbr: "h", help: "Display this message");
  var r = parser.parse(args);

  var help = r['help'];
  var missing =
      (r['issuer'] == null) || (r['account'] == null) || (r['secret'] == null);

  if (help || missing) {
    print(parser.usage);
  } else {
    var otp = buildOTP(r['issuer'], r['account'], r['secret']);
    print(otp);
    var img = toImage(otp);
    print(img);
  }
}
