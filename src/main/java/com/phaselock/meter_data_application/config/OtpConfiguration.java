package com.phaselock.meter_data_application.config;

import org.springframework.beans.factory.annotation.Value;

public class OtpConfiguration {
    public static int OTP_LENGTH;
    public static int OTP_TTL;
    public static int OTP_ATTEMPTS;
    public static int OTP_DELAY;
    public static boolean OTP_FORCE;
    public static String VERIFICATION_CODE_TEMPLATE = "Verification code: %s";

    @Value("${otp.length}")
    public void setOtpLength(int otpLength) {
        OTP_LENGTH = otpLength;
    }
    @Value("${otp.ttl}")
    public void setOtpTtl(int otpTtl) {
        OTP_TTL = otpTtl;
    }

    @Value("${otp.attempts}")
    public void setOtpAttempts(int otpAttempts) {
        OTP_ATTEMPTS = otpAttempts;
    }

    @Value("${otp.delay}")
    public void setOtpDelay(int otpDelay) {
        OTP_DELAY = otpDelay;
    }

    @Value("${otp.force}")
    public void setOtpForce(boolean otpForce) {
        OTP_FORCE = otpForce;
    }

    @Value("${otp.verification_code}")
    public void setVerificationCodeTemplate(String verificationCodeTemplate) {
        VERIFICATION_CODE_TEMPLATE = verificationCodeTemplate;
    }

}