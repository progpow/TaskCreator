package progpow.apps.mylists;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.ViewFlipper;

public class MyListsActivity extends Activity implements OnTouchListener 
{	
	int positionList=0;
	float positionForList=0;
	ListView lstView;
	EditText editNameList;
	EditText editDescList;
	ViewFlipper flipper;
	ArrayAdapter<Item> itemAdapter;
	ArrayList<Item> itemModel;
	ListView itemView;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        flipper = (ViewFlipper) findViewById(R.id.flipper);

        LayoutInflater inflater = (LayoutInflater) getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        
        flipper.addView(inflater.inflate(R.layout.listeditobject , null));
        flipper.addView(inflater.inflate(R.layout.listviewobject , null));
        flipper.addView(inflater.inflate(R.layout.itemviewscreen, null));
        flipper.setDisplayedChild(1);
        
        lstView = (ListView)findViewById(R.id.lstViewList);
        itemView = (ListView)findViewById(R.id.lstViewItems);
        final EditText editView = (EditText)findViewById(R.id.txtNameAddList);
        editNameList = (EditText)findViewById(R.id.txtNameNewList);
        editDescList = (EditText)findViewById(R.id.txtDescriptionList);
        Button btnAddList = (Button)findViewById(R.id.btnAddNewList);
        Button btnBackToList = (Button)findViewById(R.id.btnBackToList);
        final ArrayList<Model> todoItems = new ArrayList<Model>();        
        final ArrayAdapter<Model> aa;
        aa = new InteractiveArrayAdapter(this,todoItems);        
        itemModel = new ArrayList<Item>();
        itemAdapter = new ItemArrayAdapter(this, itemModel);
        
        itemView.setAdapter(itemAdapter);
        lstView.setAdapter(aa);          
        
        lstView.setOnItemClickListener(new AdapterView.OnItemClickListener() 
        {
        	@Override
        	public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        			 // Log.w("","onItemClick");
                	 // Model modelItem = (Model)lstView.getItemAtPosition(position);
                	 positionList= position;
                	 // editNameList.setText(modelItem.getName());
                	 // editDescList.setText(modelItem.getDescription());
                	 // flipper.setInAnimation(inFromLeftAnimation());    			
          			 // flipper.setOutAnimation(outToRightAnimation());
      				 // flipper.showPrevious();               	  
            }
		});
        btnAddList.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				todoItems.add(0, get(editView.getText().toString()));				
				aa.notifyDataSetChanged();
				editView.setText("");	
				
			}
    	}
		);   
        btnBackToList.setOnClickListener(new Button.OnClickListener()
    	{
    		@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub    			
    			Model modelTmp = (Model)lstView.getItemAtPosition(positionList);
    			modelTmp.setName(editNameList.getText().toString());
    			modelTmp.setDescription(editDescList.getText().toString());
    			aa.notifyDataSetChanged();
    			flipper.setInAnimation(inFromRightAnimation());                	  
           	    flipper.setOutAnimation(outToLeftAnimation());
                flipper.showNext();
			}
    	}
    	);
        lstView.setOnTouchListener(this);
        itemView.setOnTouchListener(this);    	
    }   
    private Animation inFromRightAnimation() {

    	Animation inFromRight = new TranslateAnimation(
    	Animation.RELATIVE_TO_PARENT,  +1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
    	Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
    	);

    	inFromRight.setDuration(500);
    	inFromRight.setInterpolator(new AccelerateInterpolator());
    	return inFromRight;

    	}
    private Animation outToLeftAnimation() {

    	Animation outtoLeft = new TranslateAnimation(
    	Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  -1.0f,
    	Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
    	);

    	outtoLeft.setDuration(500);
    	outtoLeft.setInterpolator(new AccelerateInterpolator());
    	return outtoLeft;

    	}
    private Animation inFromLeftAnimation() {

    	Animation inFromLeft = new TranslateAnimation(
    	Animation.RELATIVE_TO_PARENT,  -1.0f, Animation.RELATIVE_TO_PARENT,  0.0f,
    	Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
    	);

    	inFromLeft.setDuration(500);
    	inFromLeft.setInterpolator(new AccelerateInterpolator());
    	return inFromLeft;
    	}
    private Animation outToRightAnimation() {
    	Animation outtoRight = new TranslateAnimation(
    	Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,  +1.0f,
    	Animation.RELATIVE_TO_PARENT,  0.0f, Animation.RELATIVE_TO_PARENT,   0.0f
    	);
    	outtoRight.setDuration(500);
    	outtoRight.setInterpolator(new AccelerateInterpolator());
    	return outtoRight;

    	}    
    @Override
    public boolean onTouch(View v, MotionEvent event)
    {
    	int Action = event.getAction();
    	if(v==lstView)
    	{
    	    	
    	switch(Action)
    	{
    		case MotionEvent.ACTION_DOWN:
    			positionForList = event.getX();
    			break;
    		case MotionEvent.ACTION_OUTSIDE:
    		case MotionEvent.ACTION_CANCEL:
    		case MotionEvent.ACTION_UP:
    			float lastPos = event.getX();
    			if(lastPos - positionForList>10)
    			{
    				///right
    				Log.w("","onItemClick");    				
               	 	Model modelItem = (Model)lstView.getItemAtPosition(positionList);               	 	
               	 	editNameList.setText(modelItem.getName());
               	 	editDescList.setText(modelItem.getDescription());
               	 	flipper.setInAnimation(inFromLeftAnimation());    			
         			flipper.setOutAnimation(outToRightAnimation());
     				flipper.showPrevious();
    			}
    			else
    			{
    				if(lastPos-positionForList<-10)
    				{    					
    					/// left    					
    					TextView titleItems =  (TextView)findViewById(R.id.txtTitleItems);
    					Model modelItem = (Model)lstView.getItemAtPosition(positionList);
    					titleItems.setText(modelItem.getName());
    					ArrayList<Item> itemsList = modelItem.getItems();
    					itemsList.add(new Item("First"));
    					itemsList.add(new Item("Second"));
    					itemsList.add(new Item("Third"));
    					itemModel.clear();
    					itemModel.addAll(itemsList);
    					itemAdapter.notifyDataSetChanged();
    					flipper.setInAnimation(inFromRightAnimation());                	  
    	           	    flipper.setOutAnimation(outToLeftAnimation());
    	                flipper.showNext();
    				}
    			}    			
    			break;
    	}
    	}
    	else
    	{
    		if(v==itemView)
    		{
    				switch(Action)
    		    	{
    		    		case MotionEvent.ACTION_DOWN:
    		    			positionForList = event.getX();
    		    			break;
    		    		case MotionEvent.ACTION_OUTSIDE:
    		    		case MotionEvent.ACTION_CANCEL:
    		    		case MotionEvent.ACTION_UP:
    		    			float lastPos = event.getX();
    		    			if(lastPos-positionForList>10)
    						{
    		    				flipper.setInAnimation(inFromLeftAnimation());    			
    		         			flipper.setOutAnimation(outToRightAnimation());
    		     				flipper.showPrevious();
    						}
				}
    		}
    	}
    	return false;
    }
    

    private Model get(String s) {
		return new Model(s);
	}
}