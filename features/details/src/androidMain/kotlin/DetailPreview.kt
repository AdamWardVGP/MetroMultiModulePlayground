import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.homelist.DetailScreen
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

@Preview
@Composable
fun PreviewDetailScreen() {
    DetailScreen(
        detailViewModel = object : DetailViewModel {
            override val sampleDataFlow: Flow<DetailUI>
                get() = flowOf(DetailUI("Name 1", "Description 1"),)
        },
        onBackAction = {}
    )
}