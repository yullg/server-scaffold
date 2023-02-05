package com.yullg.server.scaffold.spring;

/**
 * Converts from a String to a {@link Enum} with lenient conversion rules.
 * Specifically:
 * <ul>
 * <li>Uses a case insensitive search</li>
 * <li>Does not consider {@code '_'}, {@code '$'} or other special characters</li>
 * <li>Allows mapping of {@code "false"} and {@code "true"} to enums {@code ON} and
 * {@code OFF}</li>
 * </ul>
 */
public final class LenientStringToEnumConverterFactory extends LenientObjectToEnumConverterFactory<String> {
}
