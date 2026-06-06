package com.valu.valulibrary.usecases

import com.valu.valulibrary.model.Category
import com.valu.valulibrary.model.Param
import com.valu.valulibrary.model.Product
import com.valu.valulibrary.usecases.db.ICategoryDataBase
import com.valu.valulibrary.usecases.db.IParamDataBase
import com.valu.valulibrary.usecases.db.IProductDataBase
import com.valu.valulibrary.usecases.network.IDataNetwork


class DataUseCase(private val iDataNetwork : IDataNetwork,
                  private val iProductDataBase : IProductDataBase,
                  private val iParamDataBase : IParamDataBase,
                  private val iCategoryDataBase : ICategoryDataBase
)  {

    @Suppress("SuspiciousIndentation")
    suspend fun loadData(): Boolean{
        val dataDb =  getProduct()
        return if (dataDb.isEmpty()){
            getDataService()
        }else{
            dataDb.isNotEmpty()
        }
    }

    suspend fun updateData(){
        getDataService()
    }

    suspend fun getDataService():Boolean{
        val product = getProductService()
        val param = getParamService()
        val category = getCategoryService()
        return product && param && category
    }
    suspend fun getProductService():Boolean{
        val response =  iDataNetwork.loadData()
        deleteProduct()
        return insertProduct(response)
    }

    suspend fun getParamService():Boolean{
        val response =  iDataNetwork.loadParam()
        deleteParam()
        return insertParam(response)
    }

    suspend fun getCategoryService():Boolean{
        val response =  iDataNetwork.loadCategory()
        deleteCategory()
        return insertCategory(response)
    }
    suspend fun insertProduct(data : List<Product>) = iProductDataBase.insertContact(data)

    suspend fun insertParam(data : Param) = iParamDataBase.insertParam(data)

    suspend fun insertCategory(data : List<Category>) = iCategoryDataBase.insertCategory(data)

    suspend fun deleteProduct() = iProductDataBase.deleteContactAll()

    suspend fun deleteParam() = iParamDataBase.deleteParamAll()

    suspend fun deleteCategory() = iCategoryDataBase.deleteCategoryAll()

    suspend fun getProductType(type: String) = iProductDataBase.getProducts(type)

    suspend fun getProduct() = iProductDataBase.getProductAll()

    suspend fun getParam() = iParamDataBase.getParam()

    suspend fun getCategory() = iCategoryDataBase.getCategory()

    suspend fun getProductAllCategory(id: String) = iProductDataBase.getProductAllCategory(id)


}