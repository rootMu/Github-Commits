package com.matthew.githubcommits.network.model

data class Commit(
    val author: ExtendedUser? = null,
    val comments_url: String,
    val commit: BaseCommit,
    val committer: ExtendedUser? = null,
    val html_url: String,
    val node_id: String,
    val parents: List<Parent>,
    val sha: String,
    val url: String
)

data class BaseCommit(
    val author: User,
    val comment_count: Int,
    val committer: User,
    val message: String,
    val tree: Tree,
    val url: String,
    val verification: Verification
)

data class User(
    val date: String,
    val email: String,
    val name: String
)

data class ExtendedUser(
    val avatar_url: String? = null,
    val events_url: String? = null,
    val followers_url: String? = null,
    val following_url: String? = null,
    val gists_url: String? = null,
    val gravatar_id: String? = null,
    val html_url: String? = null,
    val id: Int,
    val login: String? = null,
    val node_id: String? = null,
    val organizations_url: String? = null,
    val received_events_url: String? = null,
    val repos_url: String? = null,
    val site_admin: Boolean,
    val starred_url: String? = null,
    val subscriptions_url: String? = null,
    val type: String? = null,
    val url: String? = null
)

data class Tree(
    val sha: String,
    val url: String
)

data class Verification(
    val payload: Any,
    val reason: String,
    val signature: Any,
    val verified: Boolean
)

data class Parent(
    val html_url: String,
    val sha: String,
    val url: String
)