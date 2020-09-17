package com.example.differentmenustest

import android.os.Bundle
import android.view.ContextMenu
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Button
import android.widget.PopupMenu
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.ActionMode


class MainActivity : AppCompatActivity(), PopupMenu.OnMenuItemClickListener {
    // create object of the ActionMode
    var mActionMode: ActionMode? = null
    private var mActionModeCallBack: ActionMode.Callback? =
        object : ActionMode.Callback {
            override fun onCreateActionMode(
                actionMode: ActionMode,
                menu: Menu
            ): Boolean {
                var inflater = menuInflater
                inflater.inflate(R.menu.actionmode_context_menu,menu)
                return true
            }

            override fun onPrepareActionMode(
                actionMode: ActionMode,
                menu: Menu
            ): Boolean {
                return false
            }

            override fun onActionItemClicked(
                actionMode: ActionMode,
                menuItem: MenuItem
            ): Boolean {
                when (menuItem.itemId) {
                    R.id.actionmodemenu1 -> {
                        Toast.makeText(this@MainActivity, "Menu Item 1 clicked", Toast.LENGTH_LONG).show()
                        actionMode.finish()
                        return true
                    }
                    R.id.actionmodemenu2 -> {
                        Toast.makeText(this@MainActivity, "Menu Item 2 clicked", Toast.LENGTH_LONG).show()
                        actionMode.finish()
                        return true
                    }

                    else -> return false
                }
            }
            override fun onDestroyActionMode(actionMode: ActionMode) {
                mActionMode = null
            }
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // handle context menu
        var tv = findViewById<TextView>(R.id.txtTitle)
        registerForContextMenu(tv)
        // on long click, show actionmode menu
        var button : Button= findViewById(R.id.btnActionModeMenu)
        button.setOnLongClickListener{
            when(mActionMode){
                null -> {
                    mActionMode = startSupportActionMode(mActionModeCallBack!!)
                     //   (mActionModeCallBack)
                    true
                }

                else -> false
            }
        }


    }

    // create option menu
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        // select option manus
        val inflater = menuInflater
        inflater.inflate(R.menu.option_menu, menu)
        return true
    }

    //handle option menu selected
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menuItem1 -> {
                Toast.makeText(this, "Menu Item 1 clicked", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menuItem2 -> {
                Toast.makeText(this, "Menu Item 2 clicked", Toast.LENGTH_LONG).show()
                return true
            }
            R.id.menuItem3 -> {
                Toast.makeText(this, "Menu Item 3 clicked", Toast.LENGTH_LONG).show()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }
    // create contextual menu
    override fun onCreateContextMenu(
        menu: ContextMenu?,
        v: View?,
        menuInfo: ContextMenu.ContextMenuInfo?
    ) {
        super.onCreateContextMenu(menu, v, menuInfo)
        var inflater = menuInflater
        inflater.inflate(R.menu.floating_context_menu,menu)
    }

    // handle clicks for contextual menu
    override fun onContextItemSelected(item: MenuItem): Boolean {

        when (item.itemId){
            R.id.contextmenu1 -> {
                Toast.makeText(this,"Contextual Menu 1 Clicked",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.contextmenu2 -> {
                Toast.makeText(this,"Contextual Menu 2 Clicked",Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return super.onContextItemSelected(item)
        }
    }

    fun showPopupMenu(view: View) {
        val popup = PopupMenu(this, view)
        popup.setOnMenuItemClickListener(this)
        popup.inflate(R.menu.popup_menu)
        popup.show()


    }

    override fun onMenuItemClick(p0: MenuItem?): Boolean {
        when (p0!!.itemId){
            R.id.popupmenu1 -> {
                Toast.makeText(this,"Popup Menu 1 Clicked",Toast.LENGTH_SHORT).show()
                return true
            }
            R.id.popupmenu2 -> {
                Toast.makeText(this,"Popup Menu 2 Clicked",Toast.LENGTH_SHORT).show()
                return true
            }
            else -> return false
        }
    }
}