package ru.tusur.bookreaderservice.util;

import java.util.List;
import java.util.Random;

public class ImageGeneratorUtil {
    // todo extra avatars
    // https://img.freepik.com/free-psd/3d-illustration-human-avatar-profile_23-2150671122.jpg?w=2000&t=st=1693473305~exp=1693473905~hmac=32b562413c5c4def0b08b1d17ebbef01c99faeb76bb96a0eb290f4696e7a4837
    // https://img.freepik.com/free-psd/3d-illustration-human-avatar-profile_23-2150671116.jpg?w=2000&t=st=1693473351~exp=1693473951~hmac=ddd8c9113b1c536a0557b420d84d31049d8153c426a571361c9a5e791b8e52bf
    // man: https://img.freepik.com/free-psd/3d-render-avatar-character_23-2150611762.jpg?w=2000&t=st=1693473885~exp=1693474485~hmac=6e46f4033ab982d3e80087f93c5be97c4e0ef6e9e9413a76727480865a560f6a
    // woman: https://img.freepik.com/free-psd/3d-render-avatar-character_23-2150611768.jpg?w=2000&t=st=1693473907~exp=1693474507~hmac=975283882732ee34600d7740290f3afaecb99b7c6aa759b5621e53202d0bc3d3

    public static final List<String> USER_AVATAR_LIST_MALE = List.of(
            "https://img.freepik.com/free-psd/3d-illustration-person-with-sunglasses_23-2149436188.jpg?w=2000&t=st=1693473205~exp=1693473805~hmac=663a6c1fe67fa67b439b27f19efcda14659ec830829fe446b37c94b16eb82562",
            "https://img.freepik.com/free-psd/3d-illustration-person-with-long-hair_23-2149436197.jpg?w=2000&t=st=1693473265~exp=1693473865~hmac=ab9bd858bce9cdf8ee58a3983c23567bad8c912aabc251b97313afdd45f9a712",
            "https://img.freepik.com/free-psd/3d-illustration-person-with-glasses_23-2149436185.jpg?w=2000&t=st=1693473280~exp=1693473880~hmac=2785e813ede785e6ddc3d7e372c7ef9efe3ed4b938fb052095f7d929b7665244",
            "https://img.freepik.com/free-psd/3d-illustration-person-with-sunglasses_23-2149436180.jpg?w=2000&t=st=1693473321~exp=1693473921~hmac=1ed8b0f2d55144b352b850e66afb0596d62d31fa2531521d2d737b5bce0eec1d",
            "https://img.freepik.com/free-psd/3d-illustration-human-avatar-profile_23-2150671128.jpg?w=2000&t=st=1693473316~exp=1693473916~hmac=b7ecbb0138af87847fd35debff5a1c912111dbfba5886852fee9d3d0588b3203",
            "https://img.freepik.com/free-psd/3d-illustration-bald-person-with-glasses_23-2149436184.jpg?w=2000&t=st=1693473324~exp=1693473924~hmac=08546676c0e84debbb18ab5b1cc99f2216e0fc8b23373ad8e3d91f6b15360228",
            "https://img.freepik.com/free-psd/3d-illustration-human-avatar-profile_23-2150671122.jpg?w=2000&t=st=1693473305~exp=1693473905~hmac=32b562413c5c4def0b08b1d17ebbef01c99faeb76bb96a0eb290f4696e7a4837",
            "https://img.freepik.com/premium-psd/people-avatar-3d-illustration_235528-1573.jpg?w=2000",
            "https://img.freepik.com/free-psd/3d-illustration-human-avatar-profile_23-2150671153.jpg?w=2000&t=st=1693473928~exp=1693474528~hmac=aa8151f64e22bccf3ca0099502f483c35d01abc77a05a20809f73860f923d080"
    );

    public static final String EVENT_DEFAULT_IMAGE = "https://img.freepik.com/premium-vector/people-reading-park_73637-401.jpg?w=2000";


    public static String getRandomAvatarUrl() {
        Random random = new Random();
        int imageIndex =  random.ints(0, USER_AVATAR_LIST_MALE.size() - 1)
                .findFirst()
                .getAsInt();
        return USER_AVATAR_LIST_MALE.get(imageIndex);
    }
}
