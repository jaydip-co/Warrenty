package com.jaydip.warrenty;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.jaydip.warrenty.Models.ItemModel;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

public class ViewFileActivity extends AppCompatActivity {
    ItemModel item;
    ImageView editButton,RecieptButton,backButton,IImage,IBill,ItemImage;
    TextView Iname,Icategory,purchasedate,expireDate,IMonth,IDetail,detailseperator,detailLable,lastUpdateDate;
    CardView ItemCard,BillCard;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_file);
        Intent intent = getIntent();
        item = (ItemModel) intent.getSerializableExtra("item");
        editButton = findViewById(R.id.Iedit);
        backButton = findViewById(R.id.back_button);
        Iname = findViewById(R.id.Iname);
        Icategory = findViewById(R.id.Icategory);
        purchasedate = findViewById(R.id.purchasedate);
        expireDate = findViewById(R.id.expireDate);
        IMonth = findViewById(R.id.IMonth);
        IDetail = findViewById(R.id.IDetail);
        detailLable = findViewById(R.id.detailLable);
        detailseperator = findViewById(R.id.detailseperator);
        IImage = findViewById(R.id.IImage);
        IBill = findViewById(R.id.IBill);
        ItemCard = findViewById(R.id.ItemCard);
        BillCard = findViewById(R.id.BillCard);
        ItemImage = findViewById(R.id.Item_image);
        RecieptButton = findViewById(R.id.recieptButton);
        lastUpdateDate = findViewById(R.id.lastUpdateDate);



        //////////////////////////////////////////////////////

        editButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getApplicationContext(),EditItem.class);
                intent1.putExtra("item",item);
                startActivity(intent1);
                finish();
            }
        });

        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        setValue();

    }
    void setValue(){
        Iname.setText(item.getIname());
        Icategory.setText(item.getCategory());
        purchasedate.setText(item.getPurchaseDate());
        expireDate.setText(item.getExpireDate());
        IMonth.setText(item.getDurationMonth()+"");
        if(item.getLastUpdateDate() != null){
            lastUpdateDate.setText(item.getLastUpdateDate());
        }
        if(item.getDetail().length() == 0){
            detailLable.setVisibility(View.GONE);
            detailseperator.setVisibility(View.GONE);
            IDetail.setVisibility(View.GONE);
        }
        else {
            IDetail.setText(item.getDetail());
        }
        if(item.getItemImage() != null){
//            ItemCard.setVisibility(View.VISIBLE);

            Bitmap ImageBit = BitmapFactory.decodeByteArray(item.getItemImage(),0,item.getItemImage().length);
            IImage.setImageBitmap(ImageBit);
            ItemImage.setImageBitmap(ImageBit);
            ItemImage.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getApplicationContext(),ImageActivity.class);
                    intent.putExtra("image",item.getItemImageUri());
                    startActivity(intent);
                }
            });
        }
        if(item.getBillImage() != null){
//            BillCard.setVisibility(View.VISIBLE);
//            Bitmap ImageBit = BitmapFactory.decodeByteArray(item.getBillImage(),0,item.getBillImage().length);
//            IBill.setImageBitmap(ImageBit);
//            IBill.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    Intent intent = new Intent(getApplicationContext(),ImageActivity.class);
//                    intent.putExtra("image",item.getBillUri());
//                    intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//                    startActivity(intent);
//                }
//            });
            if(item.isBillPdf()){
                RecieptButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(),pdfViewActivity.class);
                        intent.putExtra(pdfViewActivity.PDF_URI,item.getBillUri());
                    }
                });

            }
            else {
                RecieptButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(getApplicationContext(), ImageActivity.class);
                        intent.putExtra("image", item.getBillUri());
                        intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                        startActivity(intent);
                        finish();
                    }
                });
            }

        }

    }


}