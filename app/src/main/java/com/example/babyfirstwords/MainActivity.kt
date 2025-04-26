<?xml version="1.0" encoding="utf-8"?>
<RelativeLayout xmlns:android="http://schemas.android.com/apk/res/android"
xmlns:tools="http://schemas.android.com/tools"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:background="#FFFFFF"
tools:context=".MainActivity">

<!-- Main image display area -->
<ImageView
android:id="@+id/wordImageView"
android:layout_width="match_parent"
android:layout_height="match_parent"
android:layout_margin="40dp"
android:contentDescription="@string/image_description"
android:scaleType="fitCenter" />

<!-- Word text label (optional, can be hidden) -->
<TextView
android:id="@+id/wordTextView"
android:layout_width="wrap_content"
android:layout_height="wrap_content"
android:layout_alignParentBottom="true"
android:layout_centerHorizontal="true"
android:layout_marginBottom="20dp"
android:textSize="36sp"
android:textStyle="bold"
android:textColor="#333333" />

<!-- Previous button - left corner -->
<ImageButton
android:id="@+id/prevButton"
android:layout_width="80dp"
android:layout_height="80dp"
android:layout_alignParentStart="true"
android:layout_alignParentBottom="true"
android:background="@drawable/rounded_button"
android:contentDescription="@string/previous_button"
android:src="@drawable/ic_arrow_left" />

<!-- Next button - right corner -->
<ImageButton
android:id="@+id/nextButton"
android:layout_width="80dp"
android:layout_height="80dp"
android:layout_alignParentEnd="true"
android:layout_alignParentBottom="true"
android:background="@drawable/rounded_button"
android:contentDescription="@string/next_button"
android:src="@drawable/ic_arrow_right" />

<!-- Language selection buttons - top corners -->
<ImageButton
android:id="@+id/englishButton"
android:layout_width="80dp"
android:layout_height="80dp"
android:layout_alignParentStart="true"
android:layout_alignParentTop="true"
android:background="@drawable/rounded_button"
android:contentDescription="@string/english_button"
android:src="@drawable/ic_flag_en" />

<ImageButton
android:id="@+id/norwegianButton"
android:layout_width="80dp"
android:layout_height="80dp"
android:layout_alignParentEnd="true"
android:layout_alignParentTop="true"
android:background="@drawable/rounded_button"
android:contentDescription="@string/norwegian_button"
android:src="@drawable/ic_flag_no" />

</RelativeLayout>