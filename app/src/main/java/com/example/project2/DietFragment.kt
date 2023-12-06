package com.example.project2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.github.mikephil.charting.charts.LineChart
import com.github.mikephil.charting.data.LineData
import com.github.mikephil.charting.data.LineDataSet
import com.github.mikephil.charting.data.Entry
import android.graphics.Color
import com.github.mikephil.charting.components.AxisBase
import com.github.mikephil.charting.components.XAxis
import com.github.mikephil.charting.formatter.ValueFormatter


class DietFragment : Fragment() {
    private lateinit var v: View
    private lateinit var lineChart: LineChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view= inflater.inflate(R.layout.fragment_diet, container, false)
        lineChart = view.findViewById(R.id.chart)
        initializeLineChart()

        val entries = listOf(
            Entry(1f, 5f),
            Entry(2f, 3f),
            Entry(3f, 2f),
        )

        val dataSet = LineDataSet(entries, "Diet 성분")
        dataSet.lineWidth = 10f
        dataSet.circleRadius = 6f
        dataSet.setCircleColor(Color.parseColor("#000000"))
        dataSet.setDrawCircleHole(true)
        dataSet.color = Color.parseColor("#FFF064")

        val lineData = LineData(dataSet)
        lineChart.data = lineData

        val xAxis = lineChart.xAxis
        xAxis.valueFormatter = MyXAxisValueFormatter()
        xAxis.position = XAxis.XAxisPosition.BOTTOM
        xAxis.granularity = 1f
        xAxis.textSize = 12f


        lineChart.invalidate()

        return view

    }



    private fun initializeLineChart() {
    }
    class MyXAxisValueFormatter : ValueFormatter() {
        override fun getAxisLabel(value: Float, axis: AxisBase?): String {
            return when (value) {
                1f -> "탄수화물"
                2f -> "단백질"
                3f -> "지방"
                else -> ""
            }
        }
    }
}