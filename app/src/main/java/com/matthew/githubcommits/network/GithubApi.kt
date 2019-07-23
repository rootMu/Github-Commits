package com.matthew.githubcommits.network

import com.matthew.githubcommits.network.model.Commit
import kotlinx.coroutines.Deferred
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    /**
     * Get a list of the commits on a given repo from the API
     *  optional query parameters are set here using null
     *      Retrofit skips null parameters and ignores them while assembling the request.
     *
     * @param owner - the owner of the repository being queried
     * @param repo - the name of the repository being queried
     * @param sha - Optional SHA or branch to start listing commits from.
     * @param author - Optional GitHub login or email address by which to filter by commit author.
     * @param since - Optional Only commits after this date will be returned.
     *                This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ.
     * @param until - Optional Only commits before this date will be returned.
     *                This is a timestamp in ISO 8601 format: YYYY-MM-DDTHH:MM:SSZ
     */
    @GET("/repos/{owner}/{repo}/commits")
    fun getCommits(@Path("owner") owner: String, @Path("repo") repo: String,
                   @Query("sha")branch: String? = null,
                   @Query("author")author: String? = null,
                   @Query("since")since: String? = null,
                   @Query("until")until: String? = null): Deferred<Response<List<Commit>>>

}