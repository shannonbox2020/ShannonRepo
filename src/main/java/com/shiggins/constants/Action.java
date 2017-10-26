package com.shiggins.constants;

/**
 * Represents a fragment of computation executed only for its side effects.
 */
public interface Action {
    void invoke();
}
