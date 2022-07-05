package com.sajib.ut_testandroid

import android.util.Log
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

class WeekDayViewModelTest{

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private var viewModel : WeekDayViewModel? = null
    @Before
    fun setUp(){
        viewModel = WeekDayViewModel()
    }

    @Test
    fun getWeekDays_validDiffrenetDate_returnCorrectCount(){

        val startDate: Date? = viewModel?.getDate(10,5,2022)
        val endDate: Date? = viewModel?.getDate(10,6,2022)

        viewModel?.getWorkingDaysBetweenTwoDates(startDate,endDate)
        assertEquals(21,viewModel?.weekDaysCount?.getOrAwaitValue()?.getContentIfNotHandled())
    }

    @Test
    fun getWeekDays_anzacDay_returnCorrect(){

        val startDate: Date? = viewModel?.getDate(20,4,2022)
        val endDate: Date? = viewModel?.getDate(27,4,2022)

        viewModel?.getWorkingDaysBetweenTwoDates(startDate,endDate)
        assertEquals(3,viewModel?.weekDaysCount?.getOrAwaitValue()?.getContentIfNotHandled())
    }


    @Test
    fun getWeekDays_newYear_returnCorrect(){

        val startDate: Date? = viewModel?.getDate(1,1,2023)
        val endDate: Date? = viewModel?.getDate(5,1,2023)

        viewModel?.getWorkingDaysBetweenTwoDates(startDate,endDate)
        assertEquals(3,viewModel?.weekDaysCount?.getOrAwaitValue()?.getContentIfNotHandled())
    }

    @Test
    fun getWeekDays_birthDay_returnCorrect(){

        val startDate: Date? = viewModel?.getDate(12,6,2022)
        val endDate: Date? = viewModel?.getDate(15,6,2022)

        viewModel?.getWorkingDaysBetweenTwoDates(startDate,endDate)
        assertEquals(2,viewModel?.weekDaysCount?.getOrAwaitValue()?.getContentIfNotHandled())
    }

    @Test
    fun getWeekDays_minAndMaxDate_returnCorrectCount(){

        val startDate: Date? = viewModel?.getDate(1,1,0)
        val endDate: Date? = viewModel?.getDate(1,1,9999)

        viewModel?.getWorkingDaysBetweenTwoDates(startDate,endDate)
        assertEquals(2581468,viewModel?.weekDaysCount?.getOrAwaitValue()?.getContentIfNotHandled())
    }

    @Test
    fun getWeekDays_InvalidDayLower_returnMinusOne(){

        val startDate: Date? = viewModel?.getDate(0,1,0)
        val endDate: Date? = viewModel?.getDate(1,9,2022)

        viewModel?.getWorkingDaysBetweenTwoDates(startDate,endDate)
        assertEquals(-1,viewModel?.weekDaysCount?.getOrAwaitValue()?.getContentIfNotHandled())
    }

    @Test
    fun getWeekDays_InvalidDayHigher_returnMinusOne(){

        val startDate: Date? = viewModel?.getDate(32,1,0)
        val endDate: Date? = viewModel?.getDate(1,9,2022)

        viewModel?.getWorkingDaysBetweenTwoDates(startDate,endDate)
        assertEquals(-1,viewModel?.weekDaysCount?.getOrAwaitValue()?.getContentIfNotHandled())
    }

    @Test
    fun getWeekDays_InvalidMonthLower_returnMinusOne(){

        val startDate: Date? = viewModel?.getDate(1,0,0)
        val endDate: Date? = viewModel?.getDate(1,9,2022)

        viewModel?.getWorkingDaysBetweenTwoDates(startDate,endDate)
        assertEquals(-1,viewModel?.weekDaysCount?.getOrAwaitValue()?.getContentIfNotHandled())
    }

    @Test
    fun getWeekDays_InvalidMonthHigher_returnMinusOne(){

        val startDate: Date? = viewModel?.getDate(1,13,0)
        val endDate: Date? = viewModel?.getDate(1,9,2022)

        viewModel?.getWorkingDaysBetweenTwoDates(startDate,endDate)
        assertEquals(-1,viewModel?.weekDaysCount?.getOrAwaitValue()?.getContentIfNotHandled())
    }

    @Test
    fun getWeekDays_InvalidYearLower_returnMinusOne(){

        val startDate: Date? = viewModel?.getDate(1,11,-1)
        val endDate: Date? = viewModel?.getDate(1,9,2022)
        viewModel?.getWorkingDaysBetweenTwoDates(startDate,endDate)
        assertEquals(-1,viewModel?.weekDaysCount?.getOrAwaitValue()?.getContentIfNotHandled())

    }

    @Test
    fun getWeekDays_InvalidYearHigher_returnMinusOne(){

        val startDate: Date? = viewModel?.getDate(1,11,0)
        val endDate: Date? = viewModel?.getDate(1,9,99999)
        viewModel?.getWorkingDaysBetweenTwoDates(startDate,endDate)
        assertEquals(-1,viewModel?.weekDaysCount?.getOrAwaitValue()?.getContentIfNotHandled())
    }


}