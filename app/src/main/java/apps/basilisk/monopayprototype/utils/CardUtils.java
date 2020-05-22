package apps.basilisk.monopayprototype.utils;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;

import java.util.Calendar;

import apps.basilisk.monopayprototype.R;

public class CardUtils {
    private static final String[] CVV4_BINS = new String[]{"32", "33", "34", "37"};

    public enum Type {
        VISA {
            @Override
            protected boolean is(String cardNumber) {
                return cardNumber.charAt(0) == '4';
            }
        },
        MASTERCARD {
            @Override
            protected boolean is(String cardNumber) {
                if (cardNumber.charAt(0) != '5') {
                    return false;
                }
                final char a = cardNumber.charAt(1);
                return '0' <= a && a <= '5';
            }
        },
        MAESTRO {
            @Override
            protected boolean is(String cardNumber) {
                return cardNumber.charAt(0) == '6';
            }
        },
        UNKNOWN {
            @Override
            protected boolean is(String cardNumber) {
                return true;
            }
        };

        abstract protected boolean is(String cardNumber);

        private static Type fromCardNumber(String cardNumber) {
            for (Type type : values()) {
                if (type.is(cardNumber)) {
                    return type;
                }
            }
            return null;
        }
    }

    static boolean isCvv4Length(String cardNumber) {
        for (String bin : CVV4_BINS) {
            if (cardNumber.startsWith(bin)) {
                return true;
            }
        }
        return false;
    }

    public static boolean isValidExpireMonth(int mm) {
        return mm >= 1 && mm <= 12;
    }

    private static boolean isValidExpireYearValue(int yy) {
        return yy >= 18 && yy <= 99;
    }

    public static boolean isValidExpireYear(int yy) {
        if (!isValidExpireYearValue(yy)) {
            return false;
        }
        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR) - 2000;
        return year <= yy;
    }

    public static boolean isValidExpireDate(int mm, int yy) {
        if (!isValidExpireMonth(mm)) {
            return false;
        }
        if (!isValidExpireYear(yy)) {
            return false;
        }

        final Calendar calendar = Calendar.getInstance();
        final int year = calendar.get(Calendar.YEAR) - 2000;

        return (yy > year) || (yy >= year && mm >= calendar.get(Calendar.MONTH) + 1);
    }

    public static boolean isValidCvv(String cardNumber, String cvv) {
        if (cvv == null) {
            return false;
        }
        final int length = cvv.length();
        if (isCvv4Length(cardNumber.replace(" ", ""))) {
            return length == 4;
        } else {
            return length == 3;
        }
    }

    private static boolean lunaCheck(String cardNumber) {
        final char[] cardChars = cardNumber.toCharArray();

        int sum = 0;
        boolean odd = true;
        for (int i = cardChars.length - 1; i >= 0; --i) {
            final char a = cardChars[i];

            if (!('0' <= a && a <= '9')) {
                return false;
            }
            int num = (a - '0');
            odd = !odd;
            if (odd) {
                num *= 2;
            }
            if (num > 9) {
                num -= 9;
            }
            sum += num;
        }

        return sum % 10 == 0;
    }

    public static boolean isValidCardNumber(String cardNumber) {
        if (cardNumber == null) {
            return false;
        }

        final int length = cardNumber.length();
        if (!(12 <= length && length <= 19)) {
            return false;
        }

        if (!lunaCheck(cardNumber)) {
            return false;
        }

        return true;
    }

    public static boolean isValidCard(String cardNumber, int mm, int yy, String cvv) {
        return isValidExpireDate(mm, yy) && isValidCvv(cardNumber, cvv) && isValidCardNumber(cardNumber);
    }

    public static Type getType(String cardNumber) {
        if (!isValidCardNumber(cardNumber)) {
            throw new IllegalStateException("CardNumber should be valid before for getType");
        }
        return Type.fromCardNumber(cardNumber);
    }

    public static class CardNumberFieldWatcher implements TextWatcher {
        private ImageView imageView;

        public CardNumberFieldWatcher(ImageView imageView) {
            this.imageView = imageView;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) {

        }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {

        }

        @Override
        public void afterTextChanged(Editable s) {
            updateCardIcon(s.toString().replace(" ", ""));
            if (s.length() > 16) {
                return;
            }
            for (int i = 4; i < s.length(); i += 5) {
                if (s.toString().charAt(i) != ' ') {
                    s.insert(i, String.valueOf(' '));
                }
            }
        }

        private void updateCardIcon(String s) {
            if (CardUtils.isValidCardNumber(s)) {
                switch (CardUtils.getType(s)) {
                    case VISA:
                        imageView.setImageResource(R.drawable.ic_card_payment_visa);
                        imageView.setVisibility(View.VISIBLE);
                        break;
                    case MASTERCARD:
                        imageView.setImageResource(R.drawable.ic_card_payment_mastercard);
                        imageView.setVisibility(View.VISIBLE);
                        break;
                    case MAESTRO:
                        imageView.setImageResource(R.drawable.ic_card_payment_maestro);
                        imageView.setVisibility(View.VISIBLE);
                        break;
                    case UNKNOWN:
                        imageView.setVisibility(View.INVISIBLE);
                        break;
                }
            } else {
                imageView.setVisibility(View.INVISIBLE);
            }
        }
    }
}
