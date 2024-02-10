package barrera.alejandro.dondeloveo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import barrera.alejandro.dondeloveo.R
import barrera.alejandro.dondeloveo.databinding.TeamMemberItemBinding
import barrera.alejandro.dondeloveo.presentation.base.BaseAdapter
import barrera.alejandro.dondeloveo.presentation.base.BaseViewHolder
import barrera.alejandro.dondeloveo.presentation.model.UiTeamMember
import coil.load

class TeamMemberAdapter(
    private val teamMemberItems: List<UiTeamMember>
) : BaseAdapter<UiTeamMember, TeamMemberItemBinding>(teamMemberItems) {

    inner class TeamMemberViewHolder(
        binding: TeamMemberItemBinding
    ) : BaseViewHolder<UiTeamMember, TeamMemberItemBinding>(binding) {
        override fun bind(item: UiTeamMember, position: Int) {
            with(binding) {
                imageView.load(teamMemberItems[position].imageUrl) {
                    crossfade(true)
                    placeholder(R.drawable.image_placeholder)
                }
                nameTextView.text = teamMemberItems[position].name
                roleTextView.text = teamMemberItems[position].role
            }
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TeamMemberViewHolder {
        val binding = inflateBinding(parent)
        return TeamMemberViewHolder(binding)
    }

    override fun inflateBinding(parent: ViewGroup): TeamMemberItemBinding {
        val layoutInflater = LayoutInflater.from(parent.context)
        return TeamMemberItemBinding.inflate(layoutInflater, parent, false)
    }
}