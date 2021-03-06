package test.assertk.assertions

import assertk.assert
import assertk.assertions.*
import assertk.assertions.support.show
import kotlin.test.Test
import kotlin.test.assertEquals
import kotlin.test.assertFails

class StringTest {

    //region isEqualTo
    @Test fun isEqualTo_same_value_passes() {
        assert("test").isEqualTo("test")
    }

    @Test fun isEqualTo_different_value_fails() {
        val error = assertFails {
            assert("").isEqualTo("test")
        }
        assertEquals("expected:<\"[test]\"> but was:<\"[]\">", error.message)
    }

    @Test fun isEqualTo_same_value_ignore_case_passes() {
        assert("Test").isEqualTo("tesT", true)
    }

    @Test fun isEqualTo_different_value_ignore_case_fails() {
        val error = assertFails {
            assert("Test").isEqualTo("tesT", false)
        }
        assertEquals("expected:<\"[tesT]\"> but was:<\"[Test]\">", error.message)
    }
    //endregion

    //region isNotEqualTo
    @Test fun isNotEqualTo_same_value_fails() {
        val error = assertFails {
            assert("test").isNotEqualTo("test")
        }
        assertEquals("expected to not be equal to:<\"test\">", error.message)
    }

    @Test fun isNotEqualTo_different_value_passes() {
        assert("").isNotEqualTo("test")
    }

    @Test fun isNotEqualTo_same_value_ignore_case_fails() {
        val error = assertFails {
            assert("Test").isNotEqualTo("tesT", true)
        }
        assertEquals("expected:<\"tesT\"> not to be equal to (ignoring case):<\"Test\">", error.message)
    }

    @Test fun isNotEqualTo_different_value_ignore_case_passes() {
        assert("Test").isNotEqualTo("tesT", false)
    }
    //endregion

    //region contains
    @Test fun contains_value_substring_passes() {
        assert("test").contains("est")
    }

    @Test fun contains_value_not_substring_fails() {
        val error = assertFails {
            assert("test").contains("not")
        }
        assertEquals("expected to contain:<\"not\"> but was:<\"test\">", error.message)
    }

    @Test fun contains_value_substring_ignore_case_passes() {
        assert("Test").contains("EST", true)
    }

    @Test fun contains_value_not_substring_ignore_case_fails() {
        val error = assertFails {
            assert("Test").contains("EST", false)
        }
        assertEquals("expected to contain:<\"EST\"> but was:<\"Test\">", error.message)
    }
    //endregion

    //region doesNotContain
    @Test fun doesNotContain_value_not_substring_passes() {
        assert("test").doesNotContain("not")
    }

    @Test fun doesNotContain_value_substring_fails() {
        val error = assertFails {
            assert("test").doesNotContain("est")
        }
        assertEquals("expected to not contain:<\"est\">", error.message)
    }

    @Test fun doesNotContain_value_substring_ignore_case_fails() {
        val error = assertFails {
            assert("Test").doesNotContain("EST", true)
        }
        assertEquals("expected to not contain:<\"EST\">", error.message)
    }

    @Test fun doesNotContain_value_not_substring_ignore_case_passes() {
        assert("Test").doesNotContain("EST", false)
    }
    //endregion

    //region startsWith
    @Test fun startsWith_value_prefix_passes() {
        assert("test").startsWith("te")
    }

    @Test fun startsWith_value_not_prefix_fails() {
        val error = assertFails {
            assert("test").startsWith("st")
        }
        assertEquals("expected to start with:<\"st\"> but was:<\"test\">", error.message)
    }

    @Test fun startsWith_value_prefix_ignore_case_passes() {
        assert("test").startsWith("TE", true)
    }

    @Test fun startsWith_value_not_prefix_ignore_case_fails() {
        val error = assertFails {
            assert("test").startsWith("TE", false)
        }
        assertEquals("expected to start with:<\"TE\"> but was:<\"test\">", error.message)
    }
    //endregion

    //region endsWith
    @Test fun endsWith_value_suffix_passes() {
        assert("test").endsWith("st")
    }

    @Test fun endsWith_value_not_suffix_fails() {
        val error = assertFails {
            assert("test").endsWith("te")
        }
        assertEquals("expected to end with:<\"te\"> but was:<\"test\">", error.message)
    }

    @Test fun endsWith_value_suffix_ignore_case_passes() {
        assert("test").endsWith("ST", true)
    }

    @Test fun endsWith_value_not_suffix_ignore_case_passes() {
        val error = assertFails {
            assert("test").endsWith("ST", false)
        }
        assertEquals("expected to end with:<\"ST\"> but was:<\"test\">", error.message)
    }
    //endregion

    //region hasLineCount
    @Test fun hasLineCount_correct_value_passes() {
        assert("").hasLineCount(1)
        assert("test test").hasLineCount(1)
        assert("test test\ntest test").hasLineCount(2)
        assert("test test\r\ntest test").hasLineCount(2)
        assert("test test\rtest test").hasLineCount(2)
    }

    @Test fun hasLineCount_wrong_value_fails() {
        val error = assertFails {
            assert("test test").hasLineCount(2)
        }
        assertEquals("expected to have line count:<2> but was:<1>", error.message)
    }
    //endregion

    //region matches
    @Test fun matches_matching_value_passes() {
        assert("1234").matches(Regex("\\d\\d\\d\\d"))
    }

    @Test fun matches_not_matching_value_fails() {
        val regex = Regex("\\d\\d\\d\\d")
        val error = assertFails {
            assert("12345").matches(regex)
        }
        assertEquals("expected to match:${show(regex)} but was:<\"12345\">", error.message)
    }
    //endregion
}
