package com.activity;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;

import com.activity.pokedex2.R;
import com.data.PokedexServiceMessage;
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
    private final String TAG = "PokedexListFragment-" + CommonValue.getInstance().getOwnner();
    private static PokedexListFragment mInstance;
    private View mView;
    private List<String> mPkmStringList;
    private ListAdapter mPokemonListViewAdapter;
    private ListView mPokemonListView;
    private PokedexMainService mService;

    private AdapterView.OnItemClickListener onItemClicked = new AdapterView.OnItemClickListener() {
        @Override
        public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
            String selectedItem = (String) adapterView.getItemAtPosition(position);
            Log.d(TAG, "The selected item is : " + selectedItem);
            if(mService != null) {
                mService.request(PokedexServiceMessage.MSG_REQUEST_DETAIL_BY_NAME, selectedItem);
            }
        }
    };

    public PokedexListFragment() {
        // Required empty public constructor
    }

    public void init(PokedexMainService service) {
        mService = service;
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
        startFragment();
        return mView;
    }

    public void setListData(List<String> list) {
        mPkmStringList = list;
    }

    public void startFragment() {
        mPokemonListView = mView.findViewById(R.id.pokedex_list);
        mPokemonListViewAdapter = new ArrayAdapter<String>(mView.getContext(), android.R.layout.simple_list_item_1, mPkmStringList);
        mPokemonListView.setAdapter(mPokemonListViewAdapter);
        mPokemonListView.setOnItemClickListener(onItemClicked);
    }
}