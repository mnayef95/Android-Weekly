package net.androidweekly.submit

import android.app.Activity
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ProgressBar
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.app.ActivityCompat
import androidx.core.widget.doAfterTextChanged
import com.google.android.material.chip.ChipGroup
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputLayout
import com.mobsandgeeks.saripaar.ValidationError
import com.mobsandgeeks.saripaar.Validator
import com.mobsandgeeks.saripaar.annotation.Url
import net.androidweekly.core.activities.BaseToolbarActivity
import net.androidweekly.core.utils.android.RevealAnimation
import net.androidweekly.core.utils.android.bindView
import net.androidweekly.core.utils.android.observe
import net.androidweekly.data.network.Resource
import javax.inject.Inject

/**
 * Project: Android Weekly
 * Created: Oct 20, 2019
 *
 * @author Mohamed Hamdan
 */
class SubmitActivity : BaseToolbarActivity(), Validator.ValidationListener {

    @Inject
    lateinit var viewModel: SubmitViewModel

    @Url(message = "Please enter valid url")
    var editTextLink: EditText? = null

    private val constraintLayoutRoot: ConstraintLayout? by bindView(R.id.constraint_layout_activity_submit_root)
    private val chipGroupType: ChipGroup? by bindView(R.id.chip_group_activity_submit_type)
    private val buttonSubmit: Button? by bindView(R.id.button_activity_submit)
    private val progressBar: ProgressBar? by bindView(R.id.progress_bar_activity_submit)

    private val validator: Validator by lazy {
        Validator(this).apply { setValidationListener(this@SubmitActivity) }
    }

    private var revealAnimation: RevealAnimation? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_submit)

        initRevealAnimation()
        initViews()
        initListeners()
    }

    private fun initRevealAnimation() {
        revealAnimation = RevealAnimation(
            constraintLayoutRoot,
            intent,
            this,
            Color.parseColor("#EEEEEE")
        )
    }

    private fun initViews() {
        editTextLink = findViewById(R.id.edit_text_activity_submit_link)
    }

    private fun initListeners() {
        buttonSubmit?.setOnClickListener { validator.validate(true) }
        editTextLink?.doAfterTextChanged {
            val view = editTextLink?.parent?.parent as? TextInputLayout?
            if (view?.isErrorEnabled == true) {
                view.error = null
                view.isErrorEnabled = false
                view.requestLayout()
            }
        }

        viewModel.submitLiveData.observe(this, this::handleResource)
    }

    private fun handleResource(resource: Resource) {
        when (resource) {
            is Resource.Loading -> {
                handleLoadingResource(resource)
            }
            is Resource.Success<*> -> {
                handleSuccessResource()
            }
            is Resource.Error -> {
                handleErrorResource(resource)
            }
        }
    }

    private fun handleLoadingResource(resource: Resource.Loading) {
        if (resource.show) {
            progressBar?.visibility = View.VISIBLE
            buttonSubmit?.text = null
            buttonSubmit?.isEnabled = false
            editTextLink?.isEnabled = false
            chipGroupType?.isEnabled = false
        } else {
            progressBar?.visibility = View.GONE
            buttonSubmit?.setText(R.string.button_activity_submit)
            buttonSubmit?.isEnabled = true
            editTextLink?.isEnabled = true
            chipGroupType?.isEnabled = true
        }
    }

    private fun handleSuccessResource() {
        Toast.makeText(this, R.string.toast_successfully_submitted_link, Toast.LENGTH_LONG).show()
        finish()
    }

    private fun handleErrorResource(resource: Resource.Error) {
        Snackbar.make(parentView!!, resource.error.resourceMessage, Snackbar.LENGTH_INDEFINITE)
            .setAction(R.string.button_view_error_retry) { onValidationSucceeded() }
            .show()
    }

    override fun onBackPressed() {
        revealAnimation?.unRevealActivity()
    }

    override fun onValidationFailed(errors: MutableList<ValidationError>?) {
        errors?.forEach {
            val view = it.view.parent.parent as TextInputLayout
            view.isErrorEnabled = true
            view.error = it.getCollatedErrorMessage(this)
        }
    }

    override fun onValidationSucceeded() {
        if (chipGroupType?.checkedChipId == View.NO_ID) {
            Toast.makeText(this, getString(R.string.validation_message_link_type), Toast.LENGTH_LONG).show()
            return
        }
        viewModel.submitLink(editTextLink?.text?.toString(), chipGroupType?.checkedChipId)
    }

    companion object {

        fun start(activity: Activity, view: View) {
            val revealX = (view.x + (view.width / 2)).toInt()
            val revealY = (view.y + (view.height / 2)).toInt()

            val intent = Intent(activity, SubmitActivity::class.java)
            intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_X, revealX)
            intent.putExtra(RevealAnimation.EXTRA_CIRCULAR_REVEAL_Y, revealY)

            ActivityCompat.startActivity(activity, intent, null)
            activity.overridePendingTransition(0, 0)
        }
    }
}
