package org.pseudolabs.flamesplus;


import java.io.IOException;

import com.google.ads.AdRequest;
import com.google.ads.AdView;
import org.json.JSONException;
import org.json.JSONObject;
import org.pseudolabs.flamesplus.R;
import com.facebook.android.AsyncFacebookRunner;
import com.facebook.android.Facebook;
import com.facebook.android.FacebookError;


import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.view.Menu;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	 int key;


	 	public int logged=0;

	    
	    
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Globals.screens=0;        

        AdView adView = (AdView)this.findViewById(R.id.adView);
        adView.loadAd(new AdRequest());


		final ImageButton btn1=(ImageButton)findViewById(R.id.imageButton1);
		final ImageButton btn2=(ImageButton)findViewById(R.id.imageButton2);
		final ImageButton btn3=(ImageButton)findViewById(R.id.imageButton3);
btn1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub

					Toast msg = Toast.makeText(MainActivity.this,
                            "Already in Mode 1!!", Toast.LENGTH_LONG);
				    msg.show();

			}
		});
		btn2.setOnClickListener(new OnClickListener() {
	
		public void onClick(View v) {
		// TODO Auto-generated method stub

			Globals.screens=1;
			btn1.setImageResource(R.drawable.btn1);
			btn2.setImageResource(R.drawable.btn2p);
			btn3.setImageResource(R.drawable.btn3);
			Toast msg = Toast.makeText(MainActivity.this,
                    "Mode 2 Activated!!", Toast.LENGTH_LONG);
		    msg.show();

		    	Intent Intent1 = new Intent();
		    	MainActivity.this.finish();
		    	Intent1.setClass(getApplicationContext(), Mode2Activity.class);
		    	startActivity(Intent1);

		}
		});
		btn3.setOnClickListener(new OnClickListener() {
	
		public void onClick(View v) {
		// TODO Auto-generated method stub

			btn1.setImageResource(R.drawable.btn1);
			btn2.setImageResource(R.drawable.btn2);
			btn3.setImageResource(R.drawable.btn3p);
			Toast msg = Toast.makeText(MainActivity.this,
                    "Mode 3 Activated!!", Toast.LENGTH_LONG);
		    msg.show();
			    Intent Intent1 = new Intent();
		    	Intent1.setClass(getApplicationContext(), Mode3Activity.class);
		    	MainActivity.this.finish();
		    	startActivity(Intent1);
		}
		});
		final EditText t1=(EditText)findViewById(R.id.editText1);
		final TextView result=(TextView)findViewById(R.id.textView3);
		final EditText t2=(EditText)findViewById(R.id.editText2);
		ImageView iv=(ImageView)findViewById(R.id.imageView1);
		iv.setOnTouchListener(new OnTouchListener() {
			
			public boolean onTouch(View v, MotionEvent event) {
				// TODO Auto-generated method stub
				String o1=t1.getText().toString().toLowerCase();
				String o2=t2.getText().toString().toLowerCase();
				String n1=o1.replaceAll("\\s", "");
				String n2=o2.replaceAll("\\s", "");
				if(n1.length()!=0 && n2.length()!=0)
				{
			    String ans;
				int afn=1;
			    char fno ='N';
			    afn=flames(n1,n2);
			    if(afn>0){
			    fno=pf(afn);
			    }			    
			    switch(fno)
			    {
			    case 'f':ans="FRIENDS";break;
			    case 'l':ans="LOVE";break;
			    case 'a':ans="AFFECTION";break;
			    case 'm':ans="MARRIAGE";break;
			    case 'e':ans="ENEMY";break;
			    case 's':ans="SIBLINGS";break;
			    default:ans="Error";break;
			    }

                    result.setText(t1.getText() + " and "+t2.getText()+" got "+ans);
				}
				else
				{
					Toast msg = Toast.makeText(MainActivity.this,
		                    "Enter valid Names!!", Toast.LENGTH_LONG);
				    msg.show();
				}
				return false;
			}
		});
		
    }
    @Override
    public void onResume() {
        super.onResume();
    }
    public char pf(int a){
		int i,len=6,t,index,index2=-1,min=1,sub=1;
		char str[]={'f','l','a','m','e','s'}; 
		while(len>1) 
		{ 
		t=a; 
		index=index2; 
			while(t!=0) 
		     { 
				if(min!=1&&sub==1) 
				{ 
					--t; 
					if(index>=len) 
						index=0; 
					++sub; 
				} 
				else 
				{ 
					++index; 
					--t; 
					if(index>=len) 
						index=0; 
				} 
		     } 
			index2=index; 
			for(i=index;i<(len-1);++i) 
				str[i]=str[i+1]; 
			--len; 
			min=0; 
			sub=1; 
		} 
		return str[0];
	}

	public int flames(String n1,String n2){
		
		int l1,l2,le1,le2,i,j,b=0,k;
		int a[];
	    a=new int[20];
	    l1=n1.length();
	    l2=n2.length();   
	    le1=l1;
	    le2=l2;
	    for(i=0;i<l1;i++)
	    {
	 	   exit:
	 	   for(j=0;j<l2;j++)
	 	   {
	 		   if(n1.charAt(i)==n2.charAt(j))
	 		   {
	 			   for(k=0;k<b;k++)
	 			   {
	 				   if(j==a[k])
	 				   {
	 					   break exit;
	 				   }
	 			   }
	 			   	le1--;
	 			   	le2--;
	 			   	a[b++]=j;		
	 			   	break;
	 		   }
	 	   }
		
	    }
		  return le1+le2;
	    
	}

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
