package main_test

import (
	"testing"

	main "github.com/dhcmrlchtdj/otpauth"
)

func TestGenerateURI(t *testing.T) {
	cases := []struct {
		issuer  string
		account string
		secret  string
		uri     string
	}{
		{
			"Example",
			"alice@google.com",
			"JBSW Y3DP EHPK 3PXP",
			"otpauth://totp/Example:alice@google.com?issuer=Example&secret=JBSWY3DPEHPK3PXP",
		},
		{
			"ACME Co",
			"john.doe@email.com",
			"HXDM VJEC JJWS RB3H WIZR 4IFU GFTM XBOZ",
			"otpauth://totp/ACME%20Co:john.doe@email.com?issuer=ACME+Co&secret=HXDMVJECJJWSRB3HWIZR4IFUGFTMXBOZ",
		},
	}

	for _, c := range cases {
		uri := main.GenerateURI(c.issuer, c.account, c.secret)
		if uri != c.uri {
			t.Logf("expected: %v", c.uri)
			t.Logf("actual:   %v", uri)
			t.Fail()
		}
	}
}
