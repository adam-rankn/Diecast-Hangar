package com.pingu.diecasthangar

import com.pingu.diecasthangar.core.util.parseDate
import org.junit.Test
import java.time.LocalDateTime
import java.util.*
import kotlin.test.assertEquals

class TestParseDate {

    private val fakeToday = LocalDateTime.parse("2022-11-05T13:41:05")



    @Test
    fun testFullDateFlag() {
        val sameDayDate: Date = GregorianCalendar(2022, Calendar.NOVEMBER, 5).time
        assertEquals("Sat November 05 2022 0:00 AM", parseDate(sameDayDate,true,fakeToday))

        val sameMonthDate: Date = GregorianCalendar(2022, Calendar.NOVEMBER, 2).time
        assertEquals("Wed November 02 2022 0:00 AM", parseDate(sameMonthDate,true,fakeToday))

        val differentMonthDate: Date = GregorianCalendar(2022, Calendar.SEPTEMBER, 17).time
        assertEquals("Sat September 17 2022 0:00 AM", parseDate(differentMonthDate,true,fakeToday))

        val differentYearDate: Date = GregorianCalendar(2020, Calendar.DECEMBER, 31).time
        assertEquals("Thu December 31 2020 0:00 AM", parseDate(differentYearDate,true,fakeToday))
    }

    @Test
    fun testSameDate () {
        val date: Date = GregorianCalendar(2022, Calendar.NOVEMBER, 5).time
        assertEquals("0:00 AM", parseDate(date,false,fakeToday))
    }

    @Test
    fun testSameYearSameMonth () {
        val date: Date = GregorianCalendar(2022, Calendar.NOVEMBER, 2).time
        assertEquals("Wed November 02", parseDate(date,false,fakeToday))
    }

    @Test
    fun testSameYearDifferentMonth () {
        val date: Date = GregorianCalendar(2022, Calendar.SEPTEMBER, 5).time
        assertEquals("September 05", parseDate(date,false,fakeToday))
    }

    @Test
    fun testDifferentYear() {
        val date: Date = GregorianCalendar(2021, Calendar.NOVEMBER, 5).time
        assertEquals("November 05 2021", parseDate(date,false,fakeToday))

    }

}


