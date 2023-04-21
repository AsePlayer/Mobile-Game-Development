package com.ryan.jokesapp.data.room

class JokesRepository (val jokeDao : JokeDaoRoom) {
    suspend fun getAll() = jokeDao.getAll()
    suspend fun findByKeyword(phrase: String) = jokeDao.getWithSearch("%"+phrase+"%")
    suspend fun getById(id: Int) = jokeDao.getById(id)
    suspend fun addOne(jokeEntity: JokeEntity) = jokeDao.addOne(jokeEntity)
    suspend fun deleteOne(jokeEntity: JokeEntity) = jokeDao.removeJoke(jokeEntity)
}