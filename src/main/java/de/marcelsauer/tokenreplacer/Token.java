package de.marcelsauer.tokenreplacer;

/**
 * Token Replacer Copyright (C) 2010 Marcel Sauer <marcel DOT sauer AT gmx DOT de>
 * 
 * This file is part of Token Replacer.
 * 
 * Token Replacer is free software: you can redistribute it and/or modify it under the terms of the GNU General Public
 * License as published by the Free Software Foundation, either version 3 of the License, or (at your option) any later
 * version.
 * 
 * Token Replacer is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied
 * warranty of MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU General Public License for more details.
 * 
 * You should have received a copy of the GNU General Public License along with Token Replacer. If not, see
 * <http://www.gnu.org/licenses/>.
 */
public class Token {

    private final String token;
    private Generator generator;
    private String fullToken;

    /**
     * @param token
     *            e.g. {amount} -> 'amount' would be the token
     */
    public Token(String token) {
        this.token = token;
    }

    /**
     * @param value
     *            the value to use for the token
     * @return the {@link #Token} to allow method chaining
     */
    public Token replacedBy(final String value) {
        this.generator = new Generator() {

            @Override
            public String generate() {
                return value;
            }

            @Override
            public void inject(String[] args) {
                // no need here as we have a static value
            }
        };
        return this;
    }

    /**
     * @return the {@link #Token}
     */
    public String getToken() {
        return token;
    }

    /**
     * @param generator
     *            the {@link #Generator} to use when replacing the value
     * @return the {@link #Token} to allow method chaining
     */
    public Token replacedBy(Generator generator) {
        this.generator = generator;
        return this;
    }

    /**
     * @return the {@link #Generator} associated with the {@link #Token}
     */
    public Generator getGenerator() {
        return generator;
    }

    @Override
    public String toString() {
        return "Token [fullToken=" + fullToken + ", generator=" + generator + ", token=" + token + "]";
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((token == null) ? 0 : token.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Token other = (Token) obj;
        if (token == null) {
            if (other.token != null)
                return false;
        } else if (!token.equals(other.token))
            return false;
        return true;
    }
}
