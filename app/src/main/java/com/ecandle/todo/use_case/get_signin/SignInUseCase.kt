package com.haznedar.myvocabularynotebook.domain.use_case.get_signin


import javax.inject.Inject

class SignInUseCase @Inject constructor(


) {

//    operator fun invoke(Url: String, AppCode: String, Email: String, Password: String): Flow<Resource<CrudModel>> = flow {
//
//        try {
//
//            emit(Resource.Loading())
//
//            val process = repository.userRegister(Url, AppCode, Email, Password)
//
//            coroutineScope {
//
//                emit(Resource.Success(process))
//
//            }
//
//        } catch (e: HttpException) {
//
//            emit(Resource.Error(e.localizedMessage ?: "Beklenmedik bir hata oluştu!"))
//
//        } catch (e: IOException) {
//
//            if (!internetCheck()) {
//
//                emit(Resource.Internet("Sunucuya ulaşılamadı. İnternet bağlantınızı kontrol ediniz!"))
//
//            }
//        }
//    }
}