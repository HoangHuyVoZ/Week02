package com.example.week02.view

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.*
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.week02.R
import com.example.week02.adapter.ArtileAdapter
import com.example.week02.interfaces.ArticleInterface
import com.example.week02.model.View.Companion.invisible
import com.example.week02.model.View.Companion.visible
import com.example.week02.model.modelClass.Doc
import com.example.week02.presenter.ArticlePresenter
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.filter_dialog.*
import java.util.*


class MainActivity : AppCompatActivity(), ArticleInterface.ArticleView {
    lateinit var layoutManager: GridLayoutManager
    lateinit var adapter: ArtileAdapter
    var presenter: ArticlePresenter? = null
//    var page: Int = 0
////    var sort: String = ""
////    var begindate: String = "20200101"
////    var q: String = ""
////    var fq: String = ""

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        presenter = ArticlePresenter(this)
        setContentView(R.layout.activity_main)
        initUI()
        presenter?.networkCall()
        progressBar.visible()
        addScrollListener()
    }

    private fun initUI() {

        layoutManager = GridLayoutManager(this,3)
        recyclerView.setHasFixedSize(true)
        recyclerView.layoutManager = layoutManager
        adapter = ArtileAdapter()
        recyclerView.adapter = adapter

    }


    override fun onDestroy() {
        super.onDestroy()
        presenter?.onDestroy()
    }

    override fun onSuccess(list: List<Doc>) {

        adapter.addList(list as ArrayList<Doc>)
        adapter.notifyDataSetChanged()
        progressBar.invisible()
    }

    override fun onFailed(msg: String) {
        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
    }

//    override fun onSearchSuccess(list: List<Doc>) {
////        adapter.addList(list as MutableList<Doc>)
////        adapter.notifyDataSetChanged()
////        progressBar.invisible()
//
//    }
//
//    override fun onSearchFailer(msg: String) {
//        Toast.makeText(this@MainActivity, msg, Toast.LENGTH_SHORT).show()
//    }
//
//    override fun onPageSuccess() {
////        page++
////        presenter?.networkCall()
//
//    }
//
//    override fun onPageFailer(msg: String) {
//
//    }


    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        val search = menu.findItem(R.id.action_search)
        val searchView = search.actionView as SearchView
        searchView.queryHint = "Search"
        searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                if (query != null) {
                    var search = query
                    presenter?.SearchCall(search)
                    progressBar.visible()
                    adapter.clear()
                }
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return true
            }
        })


        return super.onCreateOptionsMenu(menu)
    }

    private fun addScrollListener() {
        recyclerView.addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                val pastVisibleItem = layoutManager.findLastVisibleItemPosition()
                val total = adapter.itemCount
                presenter?.pageCall(pastVisibleItem, total)



                super.onScrolled(recyclerView, dx, dy)
            }

        })
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.filter_bar) {
            val mDialogView = LayoutInflater.from(this).inflate(R.layout.filter_dialog, null)
            val mBuilder = AlertDialog.Builder(this)
                .setView(mDialogView)
                .setTitle("Filter")
            val mAlertDialog = mBuilder.show()
            val listSort = arrayOf("newest", "oldest", "relevance")
            lateinit var radioButton: RadioButton
            var sort: String = ""
            var beginDate: String = "20120101"
            var fq: String = ""
            val adapterspinner = ArrayAdapter(
                this,
                android.R.layout.simple_spinner_item,
                listSort
            )

            adapterspinner.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line)
            mAlertDialog.sort_order_spinner.adapter = adapterspinner;
            mAlertDialog.sort_order_spinner.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {
                    override fun onItemSelected(
                        parent: AdapterView<*>,
                        view: View,
                        position: Int,
                        id: Long
                    ) {
                        // Display the selected item text on text view
                        sort = parent.getItemAtPosition(position).toString()
                    }

                    override fun onNothingSelected(parent: AdapterView<*>) {
                    }
                }

            mAlertDialog.radio_group.setOnCheckedChangeListener { group, checkedId ->
                radioButton = group.findViewById(checkedId) as RadioButton
                fq = radioButton.text.toString()
            }

            mAlertDialog.date_picker_edit.setOnClickListener {
                val c = Calendar.getInstance()
                val year = c[Calendar.YEAR]
                val month = c[Calendar.MONTH]
                val day = c[Calendar.DAY_OF_MONTH]

                val dpd = DatePickerDialog(
                    this,
                    DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                        var moth1: String
                        if (monthOfYear + 1 < 10) {
                            moth1 = "0${monthOfYear + 1}"
                        } else {
                            moth1 = (monthOfYear + 1).toString()
                        }
                        var date = dayOfMonth.toString()
                        var Year = year.toString()
                        mAlertDialog.date_picker_edit.setText(Year + moth1 + date)
                        beginDate = Year + moth1 + date

                    },
                    year,
                    month,
                    day
                )
                dpd.datePicker.maxDate = Calendar.getInstance().timeInMillis
                dpd.show()

            }
            mAlertDialog.save_button.setOnClickListener {
//                page = 0
//                adapter.clear()
//
//                presenter?.networkCall(API_KEY, page, begindate, sort, q, fq)
//
                mAlertDialog.dismiss()
//                progressBar.visible()
                presenter?.filterCall(sort, fq, beginDate)
                adapter.clear()
                progressBar.visible()
            }
            return true
        }
        return super.onOptionsItemSelected(item)
    }
}