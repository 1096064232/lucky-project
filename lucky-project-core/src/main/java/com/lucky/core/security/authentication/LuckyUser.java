package com.lucky.core.security.authentication;

import org.apache.commons.lang.StringUtils;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.Assert;

import java.io.Serializable;
import java.util.*;

public class LuckyUser implements LuckyUserDetails {

    private static final long serialVersionUID = 1556059459702016289L;
    /**
     * 密码
     */
    private String password;

    /**
     * 用户名
     */
    private final String username;

    /**
     * 手机号
     */
    private final String mobileNumber;

    /**
     * 使用社交登录时，系统中的用户标识
     */
    private final String userId;

    /**
     * 权限
     */
    private final Set<GrantedAuthority> authorities;

    /**
     * 账号是否没有过期
     */
    private final boolean accountNonExpired;

    /**
     * 账号是否没有被锁定
     */
    private final boolean accountNonLocked;

    /**
     * 账号的密码是否没有过期
     */
    private final boolean credentialsNonExpired;

    /**
     * 账号是否启用
     */
    private final boolean enabled;


    public LuckyUser(String username, String password, String mobileNumber, String userId, Collection<? extends GrantedAuthority> authorities) {
        this(username, password, mobileNumber, userId, true, true, true, true, authorities);
    }

    public LuckyUser(String username, String password, String mobileNumber, String userId, boolean enabled, boolean accountNonExpired, boolean credentialsNonExpired, boolean accountNonLocked, Collection<? extends GrantedAuthority> authorities) {
        if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password) && StringUtils.isNotBlank(mobileNumber) && StringUtils.isNotBlank(userId)) {
            this.username = username;
            this.mobileNumber = mobileNumber;
            this.userId = userId;
            this.password = password;
            this.enabled = enabled;
            this.accountNonExpired = accountNonExpired;
            this.credentialsNonExpired = credentialsNonExpired;
            this.accountNonLocked = accountNonLocked;
            this.authorities = Collections.unmodifiableSet(sortAuthorities(authorities));
        } else {
            throw new IllegalArgumentException("Cannot pass null or empty values to constructor");
        }
    }


    @Override
    public int hashCode() {
        return this.username.hashCode()+this.mobileNumber.hashCode()+this.userId.hashCode();
    }

    @Override
    public boolean equals(Object rhs) {
        if (rhs instanceof LuckyUser) {
            LuckyUser rhsUser = (LuckyUser) rhs;
            if (StringUtils.equals(rhsUser.getUsername(), this.username) && StringUtils.equals(rhsUser.getMobileNumber(), this.mobileNumber) && StringUtils.equals(rhsUser.getUserId(), this.userId)) {
                return true;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString()).append(": ");
        sb.append("Username: ").append(this.username).append("; ");
        sb.append("mobileNumber: ").append(this.mobileNumber).append("; ");
        sb.append("userId: ").append(this.userId).append("; ");
        sb.append("Password: [PROTECTED]; ");
        sb.append("Enabled: ").append(this.enabled).append("; ");
        sb.append("AccountNonExpired: ").append(this.accountNonExpired).append("; ");
        sb.append("credentialsNonExpired: ").append(this.credentialsNonExpired).append("; ");
        sb.append("AccountNonLocked: ").append(this.accountNonLocked).append("; ");
        if (!this.authorities.isEmpty()) {
            sb.append("Granted Authorities: ");
            boolean first = true;
            Iterator var3 = this.authorities.iterator();

            while (var3.hasNext()) {
                GrantedAuthority auth = (GrantedAuthority) var3.next();
                if (!first) {
                    sb.append(",");
                }

                first = false;
                sb.append(auth);
            }
        } else {
            sb.append("Not granted any authorities");
        }

        return sb.toString();
    }

    public static LuckyUser.LuckyUserBuilder withUserDetails(LuckyUserDetails luckyUserDetails) {
        return withUsername(luckyUserDetails.getUsername()).mobileNumber(luckyUserDetails.getMobileNumber()).userId(luckyUserDetails.getUserId()).password(luckyUserDetails.getPassword()).accountExpired(!luckyUserDetails.isAccountNonExpired()).accountLocked(!luckyUserDetails.isAccountNonLocked()).authorities(luckyUserDetails.getAuthorities()).credentialsExpired(!luckyUserDetails.isCredentialsNonExpired()).disabled(!luckyUserDetails.isEnabled());
    }

    public static LuckyUser.LuckyUserBuilder withUsername(String username) {
        return builder().username(username);
    }

    public static LuckyUser.LuckyUserBuilder builder() {
        return new LuckyUser.LuckyUserBuilder();
    }

    public static class LuckyUserBuilder {
        private String username;
        private String mobileNumber;
        private String userId;
        private String password;
        private List<GrantedAuthority> authorities;
        private boolean accountExpired;
        private boolean accountLocked;
        private boolean credentialsExpired;
        private boolean disabled;
        private PasswordEncoder passwordEncoder;

        private LuckyUserBuilder() {
            this.passwordEncoder = NoOpPasswordEncoder.getInstance();
        }

        public LuckyUser.LuckyUserBuilder username(String username) {
            Assert.notNull(username, "username cannot be null");
            this.username = username;
            return this;
        }
        public LuckyUser.LuckyUserBuilder mobileNumber(String mobileNumber) {
            Assert.notNull(mobileNumber, "mobileNumber cannot be null");
            this.mobileNumber = mobileNumber;
            return this;
        }
        public LuckyUser.LuckyUserBuilder userId(String userId) {
            Assert.notNull(userId, "userId cannot be null");
            this.userId = userId;
            return this;
        }

        public LuckyUser.LuckyUserBuilder password(String password) {
            Assert.notNull(password, "password cannot be null");
            this.password = password;
            return this;
        }

        private LuckyUser.LuckyUserBuilder passwordEncoder(PasswordEncoder encoder) {
            Assert.notNull(encoder, "encoder cannot be null");
            this.passwordEncoder = encoder;
            return this;
        }

        public LuckyUser.LuckyUserBuilder roles(String... roles) {
            List<GrantedAuthority> authorities = new ArrayList(roles.length);
            String[] var3 = roles;
            int var4 = roles.length;

            for(int var5 = 0; var5 < var4; ++var5) {
                String role = var3[var5];
                Assert.isTrue(!role.startsWith("ROLE_"), role + " cannot start with ROLE_ (it is automatically added)");
                authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
            }

            return this.authorities((Collection)authorities);
        }

        public LuckyUser.LuckyUserBuilder authorities(GrantedAuthority... authorities) {
            return this.authorities((Collection)Arrays.asList(authorities));
        }

        public LuckyUser.LuckyUserBuilder authorities(Collection<? extends GrantedAuthority> authorities) {
            this.authorities = new ArrayList(authorities);
            return this;
        }

        public LuckyUser.LuckyUserBuilder authorities(String... authorities) {
            return this.authorities((Collection) AuthorityUtils.createAuthorityList(authorities));
        }

        public LuckyUser.LuckyUserBuilder accountExpired(boolean accountExpired) {
            this.accountExpired = accountExpired;
            return this;
        }

        public LuckyUser.LuckyUserBuilder accountLocked(boolean accountLocked) {
            this.accountLocked = accountLocked;
            return this;
        }

        public LuckyUser.LuckyUserBuilder credentialsExpired(boolean credentialsExpired) {
            this.credentialsExpired = credentialsExpired;
            return this;
        }

        public LuckyUser.LuckyUserBuilder disabled(boolean disabled) {
            this.disabled = disabled;
            return this;
        }

        public LuckyUserDetails build() {
            String encodedPassword = this.passwordEncoder.encode(this.password);
            return new LuckyUser(this.username, encodedPassword,this.mobileNumber,this.userId, !this.disabled, !this.accountExpired, !this.credentialsExpired, !this.accountLocked, this.authorities);
        }
    }










    private static SortedSet<GrantedAuthority> sortAuthorities(Collection<? extends GrantedAuthority> authorities) {
        Assert.notNull(authorities, "Cannot pass a null GrantedAuthority collection");
        SortedSet<GrantedAuthority> sortedAuthorities = new TreeSet(new LuckyUser.AuthorityComparator());
        Iterator var2 = authorities.iterator();

        while (var2.hasNext()) {
            GrantedAuthority grantedAuthority = (GrantedAuthority) var2.next();
            Assert.notNull(grantedAuthority, "GrantedAuthority list cannot contain any null elements");
            sortedAuthorities.add(grantedAuthority);
        }

        return sortedAuthorities;
    }

    private static class AuthorityComparator implements Comparator<GrantedAuthority>, Serializable {

        private static final long serialVersionUID = -5532661392600477486L;

        private AuthorityComparator() {
        }

        public int compare(GrantedAuthority g1, GrantedAuthority g2) {
            if (g2.getAuthority() == null) {
                return -1;
            } else {
                return g1.getAuthority() == null ? 1 : g1.getAuthority().compareTo(g2.getAuthority());
            }
        }
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return username;
    }

    @Override
    public String getMobileNumber() {
        return mobileNumber;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }
}
