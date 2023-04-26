package com.bhavesh.favproductassignment.adapter

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatRatingBar
import androidx.appcompat.widget.LinearLayoutCompat
import androidx.recyclerview.widget.RecyclerView
import com.bhavesh.favproductassignment.R
import com.bhavesh.favproductassignment.model.Product
import com.bumptech.glide.Glide

class ProductAdapter(private val onClickListener: OnClickListener) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    private var mList = mutableListOf<Product>()

    @SuppressLint("NotifyDataSetChanged")
    fun setList(mList: List<Product>) {
        this.mList.clear()
        this.mList.addAll(mList)
        notifyDataSetChanged()
    }

    fun clearData(){
        val size: Int = this.mList.size
        this.mList.clear()
        notifyItemRangeRemoved(0,size)
    }

    fun updateLike(pos : Int){
        this.mList[pos].isInWishlist = this.mList[pos].isInWishlist
        notifyItemChanged(pos)
    }

    class ViewHolder(ItemView: View) : RecyclerView.ViewHolder(ItemView) {
        val imageView: ImageView = itemView.findViewById(R.id.imageView)
        val txtTitle: TextView = itemView.findViewById(R.id.txtTitle)
        val linMrp : LinearLayoutCompat = itemView.findViewById(R.id.linMrp)
        val txtSaleUnitPrice: TextView = itemView.findViewById(R.id.txtSaleUnitPrice)
        val txtMrp: TextView = itemView.findViewById(R.id.txtMrp)
        val txtPercentage: TextView = itemView.findViewById(R.id.txtPercentage)
        val btnLike: ImageButton = itemView.findViewById(R.id.btnLike)
        val ratingBarProduct: AppCompatRatingBar = itemView.findViewById(R.id.ratingBarProduct)
        val txtProductRatingUserCount: TextView = itemView.findViewById(R.id.txtProductRatingUserCount)
        val relLike: RelativeLayout = itemView.findViewById(R.id.relLike)
        val btnAddToCart : AppCompatButton = itemView.findViewById(R.id.btnAddToCart)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_product, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mList[position]
        if(model.isInWishlist!!){
            holder.btnLike.setImageResource(R.drawable.ic_baseline_favorite_24)
        }else{
            holder.btnLike.setImageResource(R.drawable.ic_baseline_favorite_border_24)
        }

        holder.btnAddToCart.isEnabled = model.isAddToCartEnable!!
        holder.btnAddToCart.text = model.addToCartButtonText

        holder.relLike.visibility = View.VISIBLE
        holder.linMrp.visibility = View.GONE
        holder.txtPercentage.visibility = View.GONE

        if(model.ratingCount == 0.0){
            holder.ratingBarProduct.rating = 3.7.toFloat()
            holder.txtProductRatingUserCount.text = model.ratingCount.toString().plus(" [").plus(model.totalReviewCount).plus(" Users]")
        }else {
            holder.ratingBarProduct.rating = model.ratingCount?.toFloat() ?: 0F
            holder.txtProductRatingUserCount.text = model.ratingCount.toString().plus(" [").plus(model.totalReviewCount).plus(" Users]")
        }

        holder.txtTitle.text = model.title
        holder.txtMrp.text = holder.txtMrp.context.getString(R.string.rupees).plus(model.price?.get(0)?.value)
        //holder.txtSaleUnitPrice.paintFlags = holder.txtSaleUnitPrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        val saleUnitPrice = model.saleUnitPrice
        holder.txtPercentage.text = "70%"//Constant.calculatePercentage(price,model.price)

        holder.txtSaleUnitPrice.text = holder.txtSaleUnitPrice.context.getString(R.string.rupees).plus(saleUnitPrice)
        holder.itemView.setOnClickListener {
            onClickListener.onViewDetails(position, model)
        }
        holder.btnLike.setOnClickListener {
            onClickListener.onFav(position, model)
        }

        holder.btnAddToCart.setOnClickListener {
            onClickListener.onAddToCart(position, model)
        }
        Glide.with(holder.imageView.context).asBitmap().load(model.imageURL).into(holder.imageView)

    }

    override fun getItemCount(): Int {
        return mList.size
    }

    override fun getItemViewType(position: Int): Int {
        return position
    }

    interface OnClickListener {
        fun onViewDetails(i: Int, model: Product)
        fun onFav(i: Int, model: Product)
        fun onAddToCart(i: Int, model: Product)
    }
}