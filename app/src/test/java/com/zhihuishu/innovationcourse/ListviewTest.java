package com.zhihuishu.innovationcourse;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;

public class ListviewTest {
    @Test
    public void teacherValidator_ReturnsTrue() {
        assertThat(Teacher.isTeacher("张海霞"),is(true));
    }
}

