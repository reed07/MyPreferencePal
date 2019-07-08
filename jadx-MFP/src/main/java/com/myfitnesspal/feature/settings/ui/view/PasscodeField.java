package com.myfitnesspal.feature.settings.ui.view;

import android.content.Context;
import android.text.Editable;
import android.text.TextWatcher;
import android.text.method.PasswordTransformationMethod;
import android.util.AttributeSet;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnFocusChangeListener;
import android.view.View.OnKeyListener;
import android.widget.EditText;
import android.widget.LinearLayout;
import com.myfitnesspal.android.R;
import com.myfitnesspal.feature.settings.util.OnCodeCompleteListener;

public class PasscodeField extends LinearLayout {
    /* access modifiers changed from: private */
    public EditText char1;
    /* access modifiers changed from: private */
    public EditText char2;
    /* access modifiers changed from: private */
    public EditText char3;
    /* access modifiers changed from: private */
    public EditText char4;
    OnCodeCompleteListener codeCompleteListener;

    public PasscodeField(Context context, AttributeSet attributeSet) {
        super(context, attributeSet);
        LinearLayout linearLayout = (LinearLayout) ((LayoutInflater) context.getSystemService("layout_inflater")).inflate(R.layout.custom_edit_field, this);
        this.char1 = (EditText) linearLayout.findViewById(R.id.editText1);
        this.char1.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.char2 = (EditText) linearLayout.findViewById(R.id.editText2);
        this.char2.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.char3 = (EditText) linearLayout.findViewById(R.id.editText3);
        this.char3.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.char4 = (EditText) linearLayout.findViewById(R.id.editText4);
        this.char4.setTransformationMethod(PasswordTransformationMethod.getInstance());
        this.char1.requestFocus();
        addEventListeners();
    }

    public void setOnCodeCompleteListener(OnCodeCompleteListener onCodeCompleteListener) {
        this.codeCompleteListener = onCodeCompleteListener;
    }

    private void addEventListeners() {
        this.char1.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    if (PasscodeField.this.char2.getText().toString().trim().length() == 1) {
                        PasscodeField.this.char2.requestFocus();
                    }
                    PasscodeField.this.char1.setSelection(PasscodeField.this.char1.getText().length());
                }
            }
        });
        this.char2.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    if (PasscodeField.this.char1.getText().toString().trim().length() == 0) {
                        PasscodeField.this.char2.setText("");
                        PasscodeField.this.char1.requestFocus();
                    } else if (PasscodeField.this.char3.getText().toString().trim().length() == 1) {
                        PasscodeField.this.char3.requestFocus();
                    }
                    PasscodeField.this.char2.setSelection(PasscodeField.this.char2.getText().length());
                }
            }
        });
        this.char3.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    if (PasscodeField.this.char2.getText().toString().trim().length() == 0) {
                        PasscodeField.this.char3.setText("");
                        PasscodeField.this.char2.requestFocus();
                    } else if (PasscodeField.this.char4.getText().toString().trim().length() == 1) {
                        PasscodeField.this.char4.requestFocus();
                    }
                    PasscodeField.this.char3.setSelection(PasscodeField.this.char3.getText().length());
                }
            }
        });
        this.char4.setOnFocusChangeListener(new OnFocusChangeListener() {
            public void onFocusChange(View view, boolean z) {
                if (z) {
                    if (PasscodeField.this.char3.getText().toString().trim().length() == 0) {
                        PasscodeField.this.char4.setText("");
                        PasscodeField.this.char3.requestFocus();
                    }
                    PasscodeField.this.char4.setSelection(PasscodeField.this.char4.getText().length());
                }
            }
        });
        this.char1.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() == 1) {
                    PasscodeField.this.char2.requestFocus();
                }
            }
        });
        this.char2.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() == 1) {
                    PasscodeField.this.char3.requestFocus();
                } else if (editable.toString().trim().length() == 0) {
                    PasscodeField.this.char1.requestFocus();
                }
            }
        });
        this.char3.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() == 1) {
                    PasscodeField.this.char4.requestFocus();
                } else if (editable.toString().trim().length() == 0) {
                    PasscodeField.this.char2.requestFocus();
                }
            }
        });
        this.char4.addTextChangedListener(new TextWatcher() {
            public void beforeTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void onTextChanged(CharSequence charSequence, int i, int i2, int i3) {
            }

            public void afterTextChanged(Editable editable) {
                if (editable.toString().trim().length() == 1) {
                    if (PasscodeField.this.codeCompleteListener != null) {
                        OnCodeCompleteListener onCodeCompleteListener = PasscodeField.this.codeCompleteListener;
                        StringBuilder sb = new StringBuilder();
                        sb.append(PasscodeField.this.char1.getText().toString());
                        sb.append(PasscodeField.this.char2.getText().toString());
                        sb.append(PasscodeField.this.char3.getText().toString());
                        sb.append(PasscodeField.this.char4.getText().toString());
                        onCodeCompleteListener.validate(sb.toString());
                    }
                } else if (editable.toString().trim().length() == 0) {
                    PasscodeField.this.char3.requestFocus();
                }
            }
        });
        this.char4.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 67) {
                    PasscodeField.this.char3.requestFocus();
                }
                return false;
            }
        });
        this.char3.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 67) {
                    PasscodeField.this.char2.requestFocus();
                }
                return false;
            }
        });
        this.char2.setOnKeyListener(new OnKeyListener() {
            public boolean onKey(View view, int i, KeyEvent keyEvent) {
                if (i == 67) {
                    PasscodeField.this.char1.requestFocus();
                }
                return false;
            }
        });
    }

    public void clearText() {
        this.char4.setText("");
        this.char3.setText("");
        this.char2.setText("");
        this.char1.setText("");
    }

    public void focus() {
        this.char4.requestFocus();
    }
}
