package `when`.sangdami.com.util

import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi
import java.lang.reflect.ParameterizedType

object MoshiUtils {

    private val moshi: Moshi =
        Moshi.Builder()
            .build()

    fun <T> fromJson(jsonString: String?, type: Class<T>): T?{
        jsonString ?: return null
        return getAdapterOfType(type).fromJson(jsonString)
    }

    fun <T> fromJson(jsonString: String?, type: ParameterizedType): T? {
        jsonString ?: return null
        return moshi.adapter<T>(type).fromJson(jsonString)
    }

    fun toJson(item: Any?): String? {
        item ?: return null
        return toJson(item, item.javaClass)
    }

    fun <T> toJson(item: T?, type: Class<T>): String? {
        return getAdapterOfType(type).toJson(item)
    }

    fun <T> toJson(item: T?, type: ParameterizedType): String? {
        return moshi.adapter<T>(type).toJson(item)
    }

    //Moshi에서는 기본 타입에 대한 adapter만 제공하기 때문에 해당 어뎁터로 사용.
    private fun <T> getAdapterOfType(type: Class<T>): JsonAdapter<T> {
        val convertedType = when {
            isSubClassOf(
                type,
                Map::class.java
            ) -> {
                Map::class.java
            }
            isSubClassOf(
                type,
                List::class.java
            ) -> {
                List::class.java
            }
            isSubClassOf(
                type,
                Set::class.java
            ) -> {
                Set::class.java
            }
            isSubClassOf(
                type,
                Collection::class.java
            ) -> {
                Collection::class.java
            }
            else -> {
                type
            }
        }
        return moshi.adapter<T>(convertedType)
    }

    private fun <L, R> isSubClassOf(subClass: Class<L>, superClass: Class<R>): Boolean {
        return superClass.isAssignableFrom(subClass)
    }
}