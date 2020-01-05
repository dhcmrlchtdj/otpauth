use percent_encoding::{utf8_percent_encode, NON_ALPHANUMERIC};
use qrcodegen::{QrCode, QrCodeEcc};
use qstring::QString;
use structopt::StructOpt;

#[derive(StructOpt)]
#[structopt(name = "OTPAuth")]
struct Cli {
    #[structopt(short, long)]
    issuer: String,
    #[structopt(short, long)]
    account: String,
    #[structopt(short, long)]
    secret: String,
    #[structopt(long)]
    svg: bool,
}

fn main() {
    let args = Cli::from_args();
    let otp = build_otp(&args.issuer, &args.account, &args.secret);
    eprintln!("{}", otp);
    let qr = QrCode::encode_text(&otp, QrCodeEcc::Low).unwrap();
    let text = if args.svg {
        qr.to_svg_string(1)
    } else {
        to_terminal_string(&qr)
    };
    println!("{}", text);
}

fn build_otp(issuer: &str, account: &str, secret: &str) -> String {
    // https://github.com/google/google-authenticator/wiki/Key-Uri-Format
    let parameters = QString::new(vec![
        ("secret", secret.replace(" ", "")),
        ("issuer", issuer.to_string()),
    ]);
    let issuer = utf8_percent_encode(issuer, NON_ALPHANUMERIC).to_string();
    let otp = format!("otpauth://totp/{}:{}?{}", issuer, account, parameters);
    otp
}

fn to_terminal_string(qr: &QrCode) -> String {
    // https://superuser.com/a/1420015
    let black = "\x1b[40m  \x1b[0m";
    let white = "\x1b[47m  \x1b[0m";

    let size = qr.size();
    let qrsize = (size * (size * 12/* newline + black.len() */)) as usize;
    let mut result = String::with_capacity(qrsize);
    for y in 0..size {
        for x in 0..size {
            if qr.get_module(x, y) {
                result += black
            } else {
                result += white
            }
        }
        result += "\n"
    }
    result
}

#[cfg(test)]
mod tests {
    use super::*;

    #[test]
    fn test_build_otp() {
        assert_eq!(
            build_otp("Example", "alice@google.com", "JBSW Y3DP EHPK 3PXP"),
            "otpauth://totp/Example:alice@google.com?secret=JBSWY3DPEHPK3PXP&issuer=Example"
        );

        assert_eq!(
            build_otp("ACME Co", "john.doe@email.com","HXDM VJEC JJWS RB3H WIZR 4IFU GFTM XBOZ"),
            "otpauth://totp/ACME%20Co:john.doe@email.com?secret=HXDMVJECJJWSRB3HWIZR4IFUGFTMXBOZ&issuer=ACME%20Co"
        );
    }
}
