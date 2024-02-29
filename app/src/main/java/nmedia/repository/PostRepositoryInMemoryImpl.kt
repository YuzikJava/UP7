package ru.netology.nmedia.repository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.netology.nmedia.dto.Post

class PostRepositoryInMemoryImpl : PostRepository {
    private var nextId = 1L
    private var posts = listOf(
        Post(
            id = nextId++,
            author = "Погода в Париже",
            content = "Сегодня облачно, без осадков. Сегодня облачно, без осадков. Сегодня облачно, без осадков. Сегодня облачно, без осадков. Сегодня облачно, без осадков. Сегодня облачно, без осадков. Сегодня облачно, без осадков. ",
            published = "28 февраля в 10:00",
            likedByMe = false,
            likeN = 234,
            vieeew = 623
        ),
        Post(
            id = nextId++,
            author = "Погода в Париже",
            content = "Сегодня будет легкое похолоданеие. Сегодня будет легкое похолоданеие. Сегодня будет легкое похолоданеие. Сегодня будет легкое похолоданеие. Сегодня будет легкое похолоданеие. Сегодня будет легкое похолоданеие. ",
            published = "27 февраля в 10:00",
            likedByMe = false,
            likeN = 342,
            vieeew = 453
        ),
        Post(
            id = nextId++,
            author = "Погода в Париже",
            content = "На сегодня ожидается снегопад. На сегодня ожидается снегопад. На сегодня ожидается снегопад. На сегодня ожидается снегопад. На сегодня ожидается снегопад. На сегодня ожидается снегопад. На сегодня ожидается снегопад. ",
            published = "26 февраля в 10:00",
            likedByMe = false,
            likeN = 135,
            vieeew = 344
        ),
    )

    private val data = MutableLiveData(posts)

    override fun getAll(): LiveData<List<Post>> = data
    override fun likeById(id: Long) {
        posts = posts.map {
           if (it.id != id) it else it.copy(likedByMe = !it.likedByMe)
        }
        data.value = posts
    }

    override fun save(post: Post) {
        if (post.id == 0L) {
            posts = listOf(
                post.copy(
                    id = nextId++,
                    author = "Погода в Париже",
                    likedByMe = false,
                    published = "Сейчас"
                )
            ) + posts
            data.value = posts
            return
        }

        posts = posts.map {
            if (it.id != post.id) it else it.copy(content = post.content)
        }
        data.value = posts
    }

    override fun removeById(id: Long) {
        posts = posts.filter { it.id != id }
        data.value = posts
    }

}