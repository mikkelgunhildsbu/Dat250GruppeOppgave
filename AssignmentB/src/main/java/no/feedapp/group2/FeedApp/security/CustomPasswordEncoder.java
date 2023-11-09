package no.feedapp.group2.FeedApp.security;

import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CustomPasswordEncoder implements PasswordEncoder {
    private final String pepper = PepperUtil.getPepper();

    private final PasswordEncoder delegate = PasswordEncoderFactories.createDelegatingPasswordEncoder();

    @Override
    public String encode(CharSequence rawPassword) {
        String pepperedPassword = rawPassword + pepper;
        return delegate.encode(pepperedPassword);
    }

    @Override
    public boolean matches(CharSequence rawPassword, String encodedPassword) {
        String pepperedPassword = rawPassword + pepper;
        return delegate.matches(pepperedPassword, encodedPassword);
    }
}
