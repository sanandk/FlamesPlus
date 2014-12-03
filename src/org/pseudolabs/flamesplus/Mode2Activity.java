package org.pseudolabs.flamesplus;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import android.app.AlertDialog;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;
import android.view.ViewGroup;
import com.facebook.*;
import com.facebook.model.GraphUser;
import com.facebook.widget.*;
import com.google.ads.AdRequest;
import com.google.ads.AdView;
import android.os.Bundle;
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

public class Mode2Activity extends FragmentActivity {

	 int key;
	 	public static final String APP_ID = "465554766799245";

    EditText t1,t2;
	 	public int btn=0;
    private final String PENDING_ACTION_BUNDLE_KEY = "org.pseudolabs.flamesplus:PendingAction";
    private Button pickFriendsButton,bd3;
    private LoginButton loginButton;

    private PendingAction pendingAction = PendingAction.NONE;
    private GraphUser user;
    private enum PendingAction {
        NONE,
        POST_PHOTO,
        POST_STATUS_UPDATE
    }
    private UiLifecycleHelper uiHelper;
    private Session.StatusCallback callback = new Session.StatusCallback() {
        @Override
        public void call(Session session, SessionState state, Exception exception) {
            onSessionStateChange(session, state, exception);
        }
    };

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        uiHelper = new UiLifecycleHelper(this, callback);
        uiHelper.onCreate(savedInstanceState);

        if (savedInstanceState != null) {
            String name = savedInstanceState.getString(PENDING_ACTION_BUNDLE_KEY);
            pendingAction = PendingAction.valueOf(name);
        }
        setContentView(R.layout.activity_mode2);

        loginButton = (LoginButton) findViewById(R.id.login_button);
        loginButton.setUserInfoChangedCallback(new LoginButton.UserInfoChangedCallback() {
            @Override
            public void onUserInfoFetched(GraphUser user) {
                Mode2Activity.this.user = user;
                updateUI();
                // It's possible that we were waiting for this.user to be populated in order to post a
                // status update.
                //handlePendingAction();
            }
        });


        pickFriendsButton = (Button) findViewById(R.id.button2);
        pickFriendsButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btn=1;
                onClickPickFriends();
            }
        });
        final FragmentManager fm = getSupportFragmentManager();
        Fragment fragment = fm.findFragmentById(R.id.fragment_container);
        if (fragment != null) {
            // If we're being re-created and have a fragment, we need to a) hide the main UI controls and
            // b) hook up its listeners again.
        //    controlsContainer.setVisibility(View.GONE);
            if (fragment instanceof FriendPickerFragment) {
                setFriendPickerListeners((FriendPickerFragment) fragment);
            }

        }
        fm.addOnBackStackChangedListener(new FragmentManager.OnBackStackChangedListener() {
            @Override
            public void onBackStackChanged() {
                if (fm.getBackStackEntryCount() == 0) {
                    // We need to re-show our UI.
          //          controlsContainer.setVisibility(View.VISIBLE);
                }
            }
        });
        AdView adView = (AdView)this.findViewById(R.id.adView);
        adView.loadAd(new AdRequest());
        t1=(EditText)findViewById(R.id.editText1);
		final TextView result=(TextView)findViewById(R.id.textView3);
		t2=(EditText)findViewById(R.id.editText2);

		bd3=(Button)findViewById(R.id.button3);
        bd3.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                btn=2;
                onClickPickFriends();
            }
        });

		final ImageButton btn1=(ImageButton)findViewById(R.id.imageButton1);
		final ImageButton btn2=(ImageButton)findViewById(R.id.imageButton2);
		final ImageButton btn3=(ImageButton)findViewById(R.id.imageButton3);
btn1.setOnClickListener(new OnClickListener() {
			
			public void onClick(View v) {
				// TODO Auto-generated method stub

					btn1.setImageResource(R.drawable.btn1p);
					btn2.setImageResource(R.drawable.btn2);
					btn3.setImageResource(R.drawable.btn3);
					Toast msg = Toast.makeText(Mode2Activity.this,
                            "Mode 1 Activated!!", Toast.LENGTH_LONG);
				    msg.show();
				    
				    Intent Intent1 = new Intent();
			    	Intent1.setClass(getApplicationContext(), MainActivity.class);
			    	Mode2Activity.this.finish();
			    	startActivity(Intent1);


			}
		});
		btn2.setOnClickListener(new OnClickListener() {
	
		public void onClick(View v) {
		// TODO Auto-generated method stub
		
			Toast msg = Toast.makeText(Mode2Activity.this,
                    "Already in Mode 2!!", Toast.LENGTH_LONG);
		    msg.show();
		
		}
		});
		btn3.setOnClickListener(new OnClickListener() {
	
		public void onClick(View v) {
		// TODO Auto-generated method stub

			btn1.setImageResource(R.drawable.btn1);
			btn2.setImageResource(R.drawable.btn2);
			btn3.setImageResource(R.drawable.btn3p);
			Toast msg = Toast.makeText(Mode2Activity.this,
                    "Mode 3 Activated!!", Toast.LENGTH_LONG);
		    msg.show();
		    
		    	Intent Intent1 = new Intent();
		    	Intent1.setClass(getApplicationContext(), Mode3Activity.class);
		    	Mode2Activity.this.finish();
		    	startActivity(Intent1);
		    

		}
		});

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
					Toast msg = Toast.makeText(Mode2Activity.this,
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
        uiHelper.onResume();

        updateUI();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        uiHelper.onSaveInstanceState(outState);

        outState.putString(PENDING_ACTION_BUNDLE_KEY, pendingAction.name());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        uiHelper.onActivityResult(requestCode, resultCode, data);

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
    public void onPause() {
        super.onPause();
        uiHelper.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        uiHelper.onDestroy();
    }

    private void onSessionStateChange(Session session, SessionState state, Exception exception) {
        if (pendingAction != PendingAction.NONE &&
                (exception instanceof FacebookOperationCanceledException ||
                        exception instanceof FacebookAuthorizationException)) {
            new AlertDialog.Builder(Mode2Activity.this)
                    .setTitle(R.string.cancelled)
                    .setMessage(R.string.permission_not_granted)
                    .setPositiveButton(R.string.ok, null)
                    .show();
            pendingAction = PendingAction.NONE;
        }
        updateUI();
    }

    private void updateUI() {
        Session session = Session.getActiveSession();
        boolean enableButtons = (session != null && session.isOpened());

        pickFriendsButton.setEnabled(enableButtons);
        bd3.setEnabled(enableButtons);

        if (enableButtons && user != null) {
           // profilePictureView.setProfileId(user.getId());
            //greeting.setText(getString(R.string.hello_user, user.getFirstName()));
        } else {
           // profilePictureView.setProfileId(null);
           // greeting.setText(null);
        }
    }



    private void onClickPickFriends() {
        final FriendPickerFragment fragment = new FriendPickerFragment();
        fragment.setMultiSelect(false);
        setFriendPickerListeners(fragment);

        showPickerFragment(fragment);
    }

    private void showPickerFragment(PickerFragment<?> fragment) {
        fragment.setOnErrorListener(new PickerFragment.OnErrorListener() {
            @Override
            public void onError(PickerFragment<?> pickerFragment, FacebookException error) {
                String text = getString(R.string.exception, error.getMessage());
                Toast toast = Toast.makeText(Mode2Activity.this, text, Toast.LENGTH_SHORT);
                toast.show();
            }
        });

            FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction()
                .replace(R.id.fragment_container, fragment)
                .addToBackStack(null)
                .commit();

        //controlsContainer.setVisibility(View.GONE);

        // We want the fragment fully created so we can use it immediately.
        fm.executePendingTransactions();

        fragment.loadData(false);
    }


    private void setFriendPickerListeners(final FriendPickerFragment fragment) {
        fragment.setOnDoneButtonClickedListener(new FriendPickerFragment.OnDoneButtonClickedListener() {
            @Override
            public void onDoneButtonClicked(PickerFragment<?> pickerFragment) {
                onFriendPickerDone(fragment);
            }
        });
    }
    private void onFriendPickerDone(FriendPickerFragment fragment) {
        FragmentManager fm = getSupportFragmentManager();
        fm.popBackStack();

        String results = "";

        Collection<GraphUser> selection = fragment.getSelection();
        if (selection != null && selection.size() > 0) {
            ArrayList<String> names = new ArrayList<String>();
            for (GraphUser user : selection) {
                names.add(user.getName());
            }
            results = TextUtils.join(", ", names);
        } else {
            results = getString(R.string.no_friends_selected);
        }
        if(btn==1){
            t1.setText(results);
        }
        else if(btn==2){
            t2.setText(results);
        }
    }

    private void showAlert(String title, String message) {
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(R.string.ok, null)
                .show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
}
