package net.dean.jraw.models;

import com.google.auto.value.AutoValue;
import com.squareup.moshi.Json;
import com.squareup.moshi.JsonAdapter;
import com.squareup.moshi.Moshi;
import net.dean.jraw.RedditClient;
import net.dean.jraw.databind.RedditModel;
import net.dean.jraw.references.SubmissionReference;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Date;

@AutoValue
@RedditModel
public abstract class Submission implements PublicContribution<SubmissionReference> {
    @Override
    @Json(name = "author") public abstract String getAuthor();

    /** Flair text to be displayed next to the author's name, if any */
    @Nullable
    @Json(name = "author_flair_text") public abstract String getAuthorFlairText();

    /** Submissions are archived once they reach a certain age. At that point, they become unmodifiable */
    @Json(name = "archived") public abstract boolean isArchived();

    /** If there is a logged in user and that user is able to give this submission gold */
    @Json(name = "can_gild") public abstract boolean isGildable();

    @Override
    @NotNull
    @Json(name = "created_utc") public abstract Date getCreated();

    @Json(name = "contest_mode") public abstract boolean isContestMode();

    @Override
    @NotNull
    @Json(name = "distinguished") public abstract DistinguishedStatus getDistinguished();

    /**
     * Domain of this Submission's URL. If this is a self post, this property will be equal to {@code self.{subreddit}},
     * otherwise it will be the actual link domain (e.g. "i.imgur.com")
     *
     * @see #isSelfPost()
     */
    public abstract String getDomain();

    @Override
    @Nullable
    public abstract Date getEdited();

    @NotNull
    @Override
    @Json(name = "name") public abstract String getFullName();

    @Override
    @Json(name = "gilded") public abstract short getGilded();

    /** If the current user has hidden the post */
    public abstract boolean isHidden();

    @Override
    @Json(name = "hide_score") public abstract boolean isScoreHidden();

    /** The unique base-36 ID generated by reddit for this model */
    @NotNull
    public abstract String getId();

    /** If this is a text-only post */
    @Json(name = "is_self") public abstract boolean isSelfPost();

    /** Flair to display next to the Submission, if any */
    @Nullable
    @Json(name = "link_flair_text") public abstract String getLinkFlairText();

    /** If the moderators/admins have prevented creating new comments on this submission */
    public abstract boolean isLocked();

    /** If this Submission contains adult content */
    @Json(name = "over_18") public abstract boolean isNsfw();

    /** URL relative to reddit.com to access this Submission from a web browser */
    public abstract String getPermalink();

    /** The type of content reddit thinks this submission links to */
    @Nullable
    @Json(name = "post_hint") public abstract String getPostHint();

    /** If the subreddit this Submission was posted in has been quarantined */
    public abstract boolean isQuarantine();

    /** The number of reports this post has received, or null if not one of the subreddit's moderators */
    @Nullable
    @Json(name = "num_reports") public abstract Integer getReports();

    /** The text content of the post, or null if not a self post */
    @Nullable
    @Json(name = "selftext") public abstract String getSelfText();

    /** If reddit thinks this submission is spam */
    public abstract boolean isSpam();

    /** If the creator of this submission has marked it as containing spoilers */
    public abstract boolean isSpoiler();

    /** The subreddit where this submission was posted to */
    public abstract String getSubreddit();

    /** The fullname of {@link #getSubreddit()} */
    @Json(name = "subreddit_id") public abstract String getSubredditFullName();

    /** The suggested way to sort comments, if any */
    @Nullable
    @Json(name = "suggested_sort") public abstract CommentSort getSuggestedSort();

    /** An empty string for self posts, otherwise a reddit-generated-and-hosted thumbnail */
    public abstract String getThumbnail();

    /** Title of the submission */
    public abstract String getTitle();

    /** An absolute URL to the comments for a self post, otherwise an absolute URL to the Submission content */
    public abstract String getUrl();

    /**
     * If the user has visited this Submission before. Requires a call to [net.dean.jraw.Endpoint.POST_STORE_VISITS] and
     * a subscription to reddit Gold
     */
    public abstract boolean isVisited();

    @Override
    @NotNull
    @Json(name = "likes") public abstract VoteDirection getVote();

    // TODO:
//    val userReports: List<UserReport>,
//    val modReports: List<ModReport>,
//    val media: ?
//    val preview: Preview,
//    val postHint: Hint


    @NotNull
    @Override
    public SubmissionReference toReference(@NotNull RedditClient reddit) {
        return new SubmissionReference(reddit, getId());
    }

    public static JsonAdapter<Submission> jsonAdapter(Moshi moshi) {
        return new AutoValue_Submission.MoshiJsonAdapter(moshi);
    }
}
