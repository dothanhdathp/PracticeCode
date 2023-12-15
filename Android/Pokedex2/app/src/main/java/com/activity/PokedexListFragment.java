package com.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SearchView;

import com.activity.pokedex2.R;
import com.data.PokedexServiceMessage;
import com.interfaces.IPkmMainServiceCallback;
import com.service.CommonValue;
import com.service.PokedexMainService;


import java.security.PrivilegedExceptionAction;
import java.util.List;
import java.util.PrimitiveIterator;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PokedexListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PokedexListFragment extends Fragment {
    private final String TAG = "PokedexListFragment-" + CommonValue.Arthur;
    private static PokedexListFragment mInstance;
    private View mView;
    private List<String> mPkmStringList;
    private ListAdapter mPokemonListViewAdapter;
    private ListView mPokemonListView;
    private PokedexMainService mService;
    private IPkmMainServiceCallback mAppCallback = null;
    private int LISTVIEW_MODE = 0;

    private AdapterView.OnItemClickListener onItemClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            String selectedItem = (String) adapterView.getItemAtPosition(position);
            Log.d(TAG, "The selected item is ["+position+"] - " + selectedItem);
            if(mService != null) {
                if(LISTVIEW_MODE == 0) {
                    mService.request(PokedexServiceMessage.MSG_REQUEST_DETAIL_BY_NATION_ID, (position+1));
                } else {
                    mService.request(PokedexServiceMessage.MSG_REQUEST_DETAIL_BY_NAME, selectedItem);
                }
            }
        }
    };

    /// FOR-SEARCH-VIEW
    private SearchView mSearchView;
    private SearchView.OnQueryTextListener mSearchViewListener = new SearchView.OnQueryTextListener() {
        @Override
        public boolean onQueryTextSubmit(String query) {
            if(mService != null) {
                mService.requestDelay(PokedexServiceMessage.MSG_REQUEST_SEARCH_NAME, query);
            }
            return false;
        }

        @Override
        public boolean onQueryTextChange(String newText) {
            if(mService != null) {
                mService.requestDelay(PokedexServiceMessage.MSG_REQUEST_SEARCH_NAME, newText);
            }
            return false;
        }
    };

    public PokedexListFragment() {
        // Required empty public constructor
    }

    public void init(PokedexMainService service, IPkmMainServiceCallback callback) {
        mService = service;
        mAppCallback = callback;
    }

    public void deinit(PokedexMainService service) {
        mService = null;
    }

    public static PokedexListFragment getInstance() {
        if(mInstance == null) {
            mInstance = new PokedexListFragment();
        }
        return mInstance;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment_pokedex_list, container, false);
        mSearchView = (SearchView)mView.findViewById(R.id.search_view);
        mSearchView.setOnQueryTextListener(mSearchViewListener);
        loadList(mPkmStringList, 0);
        return mView;
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mAppCallback.onReleaseScreen(this.getClass().getSimpleName());
    }

    public void setListData(List<String> list) {
        mPkmStringList = list;
    }

    public void setSearchList(List<String> list) {
        loadList(list, 1);
    }

    public void loadList(List<String> list, int mode) {
        LISTVIEW_MODE = mode;
        mPokemonListView = mView.findViewById(R.id.pokedex_list);
        mPokemonListViewAdapter = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_list_item_1, list);
        mPokemonListView.setAdapter(mPokemonListViewAdapter);
        mPokemonListView.setOnItemClickListener(onItemClicked);
    }
}