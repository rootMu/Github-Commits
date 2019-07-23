package com.matthew.githubcommits.network.model

data class Commit(
    val author: ExtendedUser,
    val comments_url: String,
    val commit: BaseCommit,
    val committer: ExtendedUser,
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
    val avatar_url: String,
    val events_url: String,
    val followers_url: String,
    val following_url: String,
    val gists_url: String,
    val gravatar_id: String,
    val html_url: String,
    val id: Int,
    val login: String,
    val node_id: String,
    val organizations_url: String,
    val received_events_url: String,
    val repos_url: String,
    val site_admin: Boolean,
    val starred_url: String,
    val subscriptions_url: String,
    val type: String,
    val url: String
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