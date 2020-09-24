package main

import (
	"flag"
	"fmt"
	"log"
	"net/url"
	"strings"

	"github.com/skip2/go-qrcode"
)

// https://github.com/google/google-authenticator/wiki/Key-Uri-Format
func GenerateURI(issuer string, account string, secret string) string {
	parameters := url.Values{}
	parameters.Add("secret", strings.ReplaceAll(secret, " ", ""))
	parameters.Add("issuer", issuer)
	param := parameters.Encode()

	return fmt.Sprintf(
		"otpauth://totp/%v:%v?%v",
		url.PathEscape(issuer),
		url.PathEscape(account),
		param)
}

func ToTerminal(qr [][]bool) string {
	black := "\x1b[40m  \x1b[0m"
	white := "\x1b[47m  \x1b[0m"

	var s strings.Builder
	for y := 0; y < len(qr); y++ {
		for x := 0; x < len(qr); x++ {
			if qr[x][y] {
				s.WriteString(black)
			} else {
				s.WriteString(white)
			}
		}
		s.WriteByte('\n')
	}

	return s.String()
}

func main() {
	issuer := flag.String("i", "", "`issuer`. eg: Google / Github / Cloudflare / ...")
	account := flag.String("a", "", "`account`. eg: user@example.com")
	secret := flag.String("s", "", "`secret`. OTP key. eg: ABCD EFGH IJKL MNOP")
	flag.Parse()
	if *issuer == "" || *account == "" || *secret == "" {
		flag.Usage()
		return
	}

	uri := GenerateURI(*issuer, *account, *secret)
	qr, err := qrcode.New(uri, qrcode.Low)
	if err != nil {
		log.Fatal(err)
		return
	}
	img := ToTerminal(qr.Bitmap())
	fmt.Println(img)
	fmt.Println(uri)
}
